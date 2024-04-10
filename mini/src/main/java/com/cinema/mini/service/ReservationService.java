package com.cinema.mini.service;

import com.cinema.mini.dto.MovieAndScreeningDto;
import com.cinema.mini.dto.ScreeningDto;
import com.cinema.mini.dto.SeatDto;

import java.util.List;

public interface ReservationService {
    List<ScreeningDto> getScreeningInfoForTheater(String movieId, String selectedDate);
    MovieAndScreeningDto getScreeningInfoForScreeningId(Long screeningId);
    List<SeatDto> getSeatInfo(Long screeningId);
}
