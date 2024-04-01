package com.cinema.mini.service;

import com.cinema.mini.dto.ScreeningDto;

import java.util.List;

public interface ReservationService {
    List<ScreeningDto> getScreeningInfoForTheater(String movieId, String selectedDate);
}
