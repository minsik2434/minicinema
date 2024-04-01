package com.cinema.mini.controller;

import com.cinema.mini.dto.ScreeningDto;
import com.cinema.mini.service.MovieService;
import com.cinema.mini.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservation")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final MovieService movieService;
    private final ReservationService reservationService;
    @GetMapping
    public String reservationForm(Model model){
        model.addAttribute("playingMovieDtos", movieService.playingMovie());
        return "view/reservation";
    }

    @GetMapping("/test")
    @ResponseBody
    public List<ScreeningDto> test(@RequestParam String movieId, @RequestParam String selectedDate){
        return reservationService.getScreeningInfoForTheater(movieId,selectedDate);
    }
}
