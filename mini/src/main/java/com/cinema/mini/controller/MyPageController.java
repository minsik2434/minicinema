package com.cinema.mini.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    @GetMapping("/payment")
    public String myPayment(){
        return "view/mypage/payment";
    }

    @GetMapping("/membership")
    public String myMembership(){
        return "view/mypage/membership";
    }
}
