package com.cinema.mini.controller;


import com.cinema.mini.domain.Member;
import com.cinema.mini.interceptor.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    @GetMapping("/payment")
    public String myPayment(){
        return "view/mypage/payment";
    }

    @GetMapping("/membership")
    public String myMembership(@SessionAttribute(name= SessionConst.SESSION_NAME) Member loginMember, Model model){

        model.addAttribute("loginMember",loginMember);

        return "view/mypage/membership";
    }

    @GetMapping("/memberinfo")
    public String myMemberInfo(@SessionAttribute(name=SessionConst.SESSION_NAME)Member loginMember, Model model){
        model.addAttribute("loginMember",loginMember);

        return "view/mypage/memberInfo";
    }
}
