package com.cinema.mini.controller;

import com.cinema.mini.domain.Member;
import com.cinema.mini.dto.LoginDto;
import com.cinema.mini.dto.MemberRegisterDto;
import com.cinema.mini.dto.ProfileUpdateDto;
import com.cinema.mini.dto.PasswordUpdateDto;
import com.cinema.mini.interceptor.SessionConst;
import com.cinema.mini.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginDto")LoginDto loginDto){
        return "view/memberManagement/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginDto loginDto, BindingResult bindingResult, Model model,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "view/memberManagement/login";
        }
        Member loginMember = memberService.memberLogin(loginDto);
        if(loginMember == null){
            bindingResult.reject("loginFail");
            return "view/memberManagement/login";
        }
        model.addAttribute("loginMember",loginMember);
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.SESSION_NAME,loginMember);
        return "redirect:"+redirectURL;
    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/join")
    public String joinForm(@ModelAttribute("memberRegisterDto") MemberRegisterDto memberRegisterDto){
        return "view/memberManagement/join";
    }

    @PostMapping("/join")
    public String memberRegister(@Validated @ModelAttribute MemberRegisterDto memberRegisterDto,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "view/memberManagement/join";
        }
        if(memberService.isDuplicateMemberId(memberRegisterDto.getLoginId())){
            bindingResult.rejectValue("loginId",  "DuplicateMemberLoginId");
            log.info("bindingResult={}",bindingResult);
            return "view/memberManagement/join";
        }
        if(!memberRegisterDto.getPassword().equals(memberRegisterDto.getRePassword())){
            bindingResult.rejectValue("rePassword","NotMatchPasswordAndRepassword");
            return "view/memberManagement/join";
        }

        memberService.memberRegister(memberRegisterDto);
        return "redirect:/";
    }

    @GetMapping("/updateprofile")
    public String updateProfileForm(@SessionAttribute(name = SessionConst.SESSION_NAME)Member loginMember, Model model){
        ProfileUpdateDto profileUpdateDto = new ProfileUpdateDto(loginMember);
        model.addAttribute("profileUpdateDto",profileUpdateDto);
        return "view/memberManagement/updateprofile";
    }

    @PostMapping("/updateprofile")
    public String updateProfile(@SessionAttribute(name = SessionConst.SESSION_NAME)Member loginMember,
                                @Validated @ModelAttribute("profileUpdateDto") ProfileUpdateDto profileUpdateDto,
                                BindingResult bindingResult,
                                HttpSession session,
                                RedirectAttributes attributes){

        if(bindingResult.hasErrors()){
            return "view/memberManagement/updateprofile";
        }
        session.removeAttribute(SessionConst.SESSION_NAME);
        memberService.profileUpdate(loginMember.getMemberId(),profileUpdateDto);
        attributes.addAttribute("re_login", true);
        return "redirect:/";
    }

    @GetMapping("/updatepassword")
    public String updatePasswordForm(@ModelAttribute("passwordUpdateDto") PasswordUpdateDto passwordUpdateDto){
        return "view/memberManagement/updatepassword";
    }

    @PostMapping("/updatepassword")
    public String updatePassword(@SessionAttribute(SessionConst.SESSION_NAME)Member loginMember,
                                 @Validated @ModelAttribute PasswordUpdateDto passwordUpdateDto,
                                 BindingResult bindingResult , HttpSession session,
                                 RedirectAttributes attributes){
        if(bindingResult.hasErrors()){
            return "view/memberManagement/updatepassword";
        }
        if(!loginMember.getPassword().equals(passwordUpdateDto.getCurPassword())){
            bindingResult.rejectValue("curPassword","NotMatchedPassword");
            return "view/memberManagement/updatepassword";
        }
        if(!passwordUpdateDto.getNewPassword().equals(passwordUpdateDto.getReNewPassword())){
            bindingResult.reject("NotMatchPasswordAndRepassword");
            return "view/memberManagement/updatepassword";
        }
        if(passwordUpdateDto.getNewPassword().equals(passwordUpdateDto.getCurPassword())){
            bindingResult.reject("samePassword");
            return "view/memberManagement/updatepassword";
        }
        memberService.passwordUpdate(loginMember.getMemberId(), passwordUpdateDto.getNewPassword());
        session.removeAttribute(SessionConst.SESSION_NAME);
        attributes.addAttribute("re_login", true);
        return "redirect:/";
    }
}
