package com.cinema.mini.controller;

import com.cinema.mini.domain.Member;
import com.cinema.mini.dto.LoginDto;
import com.cinema.mini.dto.MemberRegisterDto;
import com.cinema.mini.interceptor.SessionConst;
import com.cinema.mini.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginDto")LoginDto loginDto){
        return "view/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginDto loginDto, BindingResult bindingResult, Model model,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request){
        log.info("loginId={}",loginDto.getLoginId());
        log.info("password={}",loginDto.getPassword());
        if(bindingResult.hasErrors()){
            return "view/login";
        }
        Member loginMember = memberService.memberLogin(loginDto);
        if(loginMember == null){
            bindingResult.reject("loginFail");
            return "view/login";
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
        return "view/join";
    }

    @PostMapping("/join")
    public String userRegister(@Validated @ModelAttribute MemberRegisterDto memberRegisterDto,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "view/join";
        }
        if(memberService.isDuplicateMemberId(memberRegisterDto.getLoginId())){
            bindingResult.reject("DuplicateMemberLoginId");
            return "view/join";
        }
        if(!memberRegisterDto.getPassword().equals(memberRegisterDto.getRePassword())){
            bindingResult.reject("NotMatchPasswordAndRepassword");
            return "view/join";
        }

        memberService.memberRegister(memberRegisterDto);
        return "redirect:/";
    }
}
