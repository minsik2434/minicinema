package com.cinema.mini.controller;

import com.cinema.mini.domain.Member;
import com.cinema.mini.interceptor.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/")
public class TheaterController {

    @GetMapping
    public String home(@SessionAttribute(name = SessionConst.SESSION_NAME, required = false)Member loginMember, Model model){
        if(loginMember != null){
            model.addAttribute("loginMember",loginMember);
        }
        return "view/home";
    }

    @GetMapping("membership")
    public String membership(){
        return "view/membershipInfo";
    }
}
