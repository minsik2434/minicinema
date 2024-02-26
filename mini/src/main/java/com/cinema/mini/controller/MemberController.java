package com.cinema.mini.controller;

import com.cinema.mini.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/login")
    public String loginForm(){
        return "view/login";
    }

    @PostMapping("/login")
    public String login(){
        return null;
    }

    @PostMapping("/join")
    public String userRegister(){
        return null;
    }
}
