package com.cinema.mini.controller;

import com.cinema.mini.dto.MovieListDto;
import com.cinema.mini.service.TheaterService;
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

    final TheaterService theaterService;

    @GetMapping
    public String home(Model model){
        List<MovieListDto> lastestMovieDtos = theaterService.lastestMovieList();
        List<MovieListDto> popularMovieDtos = theaterService.popularMovieList();
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
        List<MovieListDto> romanceMovieDtos = theaterService.genreByMovieList("로맨스");
        List<MovieListDto> actionMovieDtos = theaterService.genreByMovieList("액션");
        List<MovieListDto> animationMovieDtos = theaterService.genreByMovieList("애니메이션");
        List<MovieListDto> fantasyMovieDtos = theaterService.genreByMovieList("판타지");
        model.addAttribute("romanceMovieDtos", romanceMovieDtos);
        model.addAttribute("actionMovieDtos", actionMovieDtos);
        model.addAttribute("animationMovieDtos",animationMovieDtos);
        model.addAttribute("fantasyMovieDtos",fantasyMovieDtos);
        return "view/movie";
    }

    @GetMapping("search")
    public String search(){
        return "view/search";
    }
}
