package com.cinema.mini.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ScreeningDto {
    private long theaterId;
    private String theaterName;
    List<ScreeningByTheater> screenings;

    @Data
    public static class ScreeningByTheater{
        private long screeningId;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private int maxSeat;
        private int remainSeat;
    }
}
