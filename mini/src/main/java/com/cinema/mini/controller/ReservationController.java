package com.cinema.mini.controller;

import com.cinema.mini.dto.MovieAndScreeningDto;
import com.cinema.mini.dto.ScreeningDto;
import com.cinema.mini.dto.SeatDto;
import com.cinema.mini.service.MovieService;
import com.cinema.mini.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return "view/reservation/reservation";
    }

    @GetMapping("/screening")
    @ResponseBody
    public List<ScreeningDto> screeningInfo(@RequestParam String movieId, @RequestParam String selectedDate){
        return reservationService.getScreeningInfoForTheater(movieId,selectedDate);
    }

    @PostMapping("/seat")
    public String seatForm(@RequestParam Long screeningId, Model model,
                           @CookieValue("ticketing") boolean isTicketing){
        if(!isTicketing){
            return "redirect:/reservation";
        }
        MovieAndScreeningDto movieAndScreeningDto = reservationService.getScreeningInfoForScreeningId(screeningId);
        model.addAttribute("screeningInfo",movieAndScreeningDto);

        List<SeatDto> seatInfo = reservationService.getSeatInfo(screeningId);
        Map<Integer, List<SeatDto>> seatInfoMap = seatInfo.stream().collect(Collectors.groupingBy(SeatDto::getSeatRow));
        model.addAttribute("seatInfoMap",seatInfoMap);
        return "view/reservation/seat";
    }
}
