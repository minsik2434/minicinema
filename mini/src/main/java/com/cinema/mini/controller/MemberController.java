package com.cinema.mini.controller;

import com.cinema.mini.domain.Member;
import com.cinema.mini.dto.LoginDto;
import com.cinema.mini.interceptor.SessionConst;
import com.cinema.mini.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String login(@ModelAttribute LoginDto loginDto, Model model, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request){
        log.info("loginId={}",loginDto.getLoginId());
        log.info("password={}",loginDto.getPassword());
        if(bindingResult.hasErrors()){
            return "view/login";
        }
        Member loginMember = memberService.memberLogin(loginDto);
        if(loginMember == null){
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다");
            return "view/login";
        }
        model.addAttribute("loginMember",loginMember);
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.SESSION_NAME,loginMember);
        return "redirect:"+redirectURL;
    }

    @PostMapping("/join")
    public String userRegister(){
        return null;
    }
}
