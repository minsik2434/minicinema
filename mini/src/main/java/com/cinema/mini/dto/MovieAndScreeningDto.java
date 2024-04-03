package com.cinema.mini.dto;

import com.cinema.mini.domain.Screening;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovieAndScreeningDto {
    private String title;
    private String posterPath;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long screeningId;
    private String theaterName;

    public MovieAndScreeningDto(Screening screening){
        this.title = screening.getMovie().getTitle();
        this.posterPath = screening.getMovie().getPosterPath();
        this.startTime = screening.getStartTime();
        this.endTime = screening.getEndTime();
        this.screeningId = screening.getScreeningId();
        this.theaterName = screening.getTheater().getTheaterName();
    }
}
