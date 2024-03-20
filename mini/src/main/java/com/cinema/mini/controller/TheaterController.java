package com.cinema.mini.controller;

import com.cinema.mini.dto.MovieDto;
import com.cinema.mini.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TheaterController {

    private final MovieService movieService;

    @GetMapping
    public String home(Model model){
        List<MovieDto> lastestMovieDtos = movieService.lastestMovieList();
        List<MovieDto> popularMovieDtos = movieService.popularMovieList();
        model.addAttribute("lastestMovieDtos", lastestMovieDtos);
        model.addAttribute("popularMovieDtos", popularMovieDtos);
        return "home";
    }

    @GetMapping("membership")
    public String membership(){
        return "view/membershipInfo";
    }


    @GetMapping("movie")
    public String movie(Model model){
        List<MovieDto> romanceMovieDtos = movieService.genreByMovieList("로맨스");
        List<MovieDto> actionMovieDtos = movieService.genreByMovieList("액션");
        List<MovieDto> animationMovieDtos = movieService.genreByMovieList("애니메이션");
        List<MovieDto> fantasyMovieDtos = movieService.genreByMovieList("판타지");
        model.addAttribute("romanceMovieDtos", romanceMovieDtos);
        model.addAttribute("actionMovieDtos", actionMovieDtos);
        model.addAttribute("animationMovieDtos",animationMovieDtos);
        model.addAttribute("fantasyMovieDtos",fantasyMovieDtos);
        return "view/movie";
    }

}
