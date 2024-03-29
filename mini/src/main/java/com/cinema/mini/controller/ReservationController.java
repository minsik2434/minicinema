package com.cinema.mini.controller;

import com.cinema.mini.repository.MovieRepository;
import com.cinema.mini.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final MovieService movieService;
    @GetMapping
    public String reservationForm(Model model){
        model.addAttribute("playingMovieDtos", movieService.playingMovie());
        return "view/reservation";
    }
}
