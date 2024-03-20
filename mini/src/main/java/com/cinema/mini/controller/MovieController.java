package com.cinema.mini.controller;

import com.cinema.mini.dto.MovieDto;
import com.cinema.mini.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/movie")
@Slf4j
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    @GetMapping("/search")
    public String search(@RequestParam("title") String title, Model model){
        if(title == null){
            return "view/search";
        }
        List<MovieDto> movieListDtos = movieService.searchMovieByTitle(title);
        model.addAttribute("searchMovieList", movieListDtos);
        return "view/search";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("mvid") String movieId, Model model){
        if(movieId == null){
            return "redirect:/";
        }
        //TODO service..
        return "view/movie_detail";
    }

}
