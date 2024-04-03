package com.cinema.mini.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovieAndScreeningDto {
    private String title;
    private String posterPath;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String screeningId;
    private String theaterName;
}
