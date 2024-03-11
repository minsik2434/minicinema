package com.cinema.mini.controller;

import com.cinema.mini.domain.Member;
import com.cinema.mini.dto.HomeMovieListDto;
import com.cinema.mini.interceptor.SessionConst;
import com.cinema.mini.service.TheaterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TheaterController {

    final TheaterService theaterService;

    @GetMapping
    public String home(Model model){
        List<HomeMovieListDto> lastestMovieDtos = theaterService.lastestMovieList();
        List<HomeMovieListDto> popularMovieDtos = theaterService.popularMovieList();
        model.addAttribute("lastestMovieDtos", lastestMovieDtos);
        model.addAttribute("popularMovieDtos", popularMovieDtos);
        return "view/home";
    }

    @GetMapping("membership")
    public String membership(){
        return "view/membershipInfo";
    }
}
