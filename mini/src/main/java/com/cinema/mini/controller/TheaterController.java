package com.cinema.mini.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TheaterController {

    @GetMapping("/")
    public String home(){
        return "view/home";
    }
}
