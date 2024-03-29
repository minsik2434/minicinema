package com.cinema.mini.controller;

import com.cinema.mini.repository.MovieRepository;
import com.cinema.mini.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservation")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final MovieService movieService;
    @GetMapping
    public String reservationForm(Model model){
        model.addAttribute("playingMovieDtos", movieService.playingMovie());
        return "view/reservation";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test(@RequestParam String movieTitle, @RequestParam String selectedDate){

        return "ok";
    }
}
