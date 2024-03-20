package com.cinema.mini.controller;

import com.cinema.mini.dto.MovieDetailDto;
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
    public String search(@RequestParam(value = "title",required = false) String title, Model model){
        if(title == null || title.isEmpty()){
            return "view/search";
        }
        List<MovieDto> movieListDtos = movieService.searchMovieByTitle(title);
        model.addAttribute("searchMovieList", movieListDtos);
        return "view/search";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(value = "mvid", defaultValue = "") String movieId, Model model){
        if(movieId == null || movieId.isEmpty()){
            return "redirect:/";
        }
        MovieDetailDto movieDetailDto = movieService.detailMovieInfo(movieId);
        model.addAttribute("movieDetailDto",movieDetailDto);
        return "view/movie_detail";
    }
}
