package com.cinema.mini.controller;

import com.cinema.mini.dto.MovieListDto;
import com.cinema.mini.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/movie")
@Slf4j
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    @GetMapping("/search")
    public String search(@Param("title") String title, Model model){
        if(title == null){
            return "view/search";
        }
        List<MovieListDto> movieListDtos = movieService.searchMovieByTitle(title);
        model.addAttribute("searchMovieList", movieListDtos);
        return "view/search";
    }

}
