package com.cinema.mini.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ScreeningDto {
    private long theaterId;
    List<ScreeningByTheater> screenings;

    @Data
    public static class ScreeningByTheater{
        private long screeningId;
        private String theaterName;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private int maxSeat;
        private int remainSeat;
    }
}
