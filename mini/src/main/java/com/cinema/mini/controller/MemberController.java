package com.cinema.mini.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class MemberController {

    @GetMapping("/login")
    public String login(){
        return "view/login";
    }

    @PostMapping("/join")
    public String userRegister(){
        return null;
    }
}
