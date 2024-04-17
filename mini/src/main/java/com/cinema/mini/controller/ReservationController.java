package com.cinema.mini.controller;

import com.cinema.mini.domain.Member;
import com.cinema.mini.domain.Seat;
import com.cinema.mini.dto.*;
import com.cinema.mini.exception.DuplicateException;
import com.cinema.mini.exception.NotFoundResourceException;
import com.cinema.mini.interceptor.SessionConst;
import com.cinema.mini.repository.SeatRepository;
import com.cinema.mini.service.MovieService;
import com.cinema.mini.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(NotFoundResourceException.class)
    public ResponseEntity<String> notFoundResourceHandler(NotFoundResourceException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<String> duplicateHandler(DuplicateException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    private final MovieService movieService;
    private final ReservationService reservationService;
    private final SeatRepository seatRepository;

    @GetMapping
    public String reservationForm(Model model){
        model.addAttribute("playingMovieDtos", movieService.playingMovie());
        return "view/reservation/reservation";
    }

    @GetMapping("/screening")
    public ResponseEntity<List<ScreeningDto>> screeningInfo(@RequestParam String movieId, @RequestParam String selectedDate){
        return ResponseEntity.status(HttpStatus.OK)
                .body(reservationService.getScreeningInfoForTheater(movieId,selectedDate));

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
        Map<String, List<SeatDto>> seatInfoMap = seatInfo.stream().collect(Collectors.groupingBy(SeatDto::getSeatRow));
        model.addAttribute("seatInfoMap",seatInfoMap);
        return "view/reservation/seat";
    }

    @PostMapping("/payForm")
    public String payForm(@SessionAttribute(name = SessionConst.SESSION_NAME) Member loginMember,
                          @ModelAttribute PayDto payDto, Model model){
        MovieAndScreeningDto movieAndScreeningDto = reservationService.getScreeningInfoForScreeningId(payDto.getScreeningId());
        model.addAttribute("screeningInfo",movieAndScreeningDto);

        StringBuilder st = new StringBuilder();
        Long[] selectedSeats = payDto.getSelectedSeats();
        for (Long selectedSeat : selectedSeats) {
            Seat seat = seatRepository.findById(selectedSeat).orElseThrow();
            st.append(seat.getSeatRow()).append(seat.getSeatNumber()).append(", ");
        }
        String seats = st.substring(0, st.length()-2);
        PaymentInfo paymentInfo = new PaymentInfo(loginMember,payDto.getTotalPersonNum());
        model.addAttribute("paymentInfo", paymentInfo);
        model.addAttribute("selectedSeats",seats);
        model.addAttribute("seatIds",selectedSeats);
        model.addAttribute("totalPersonNum", payDto.getTotalPersonNum());

        return "view/reservation/pay";
    }

    @PostMapping("/payment")
    public ResponseEntity<String> payment(@SessionAttribute(name= SessionConst.SESSION_NAME) Member loginMember,
                                  @RequestBody PaymentDto paymentDto){
        reservationService.reserve(loginMember,paymentDto);
        return ResponseEntity.ok().body("Reservation Success");
    }
}
