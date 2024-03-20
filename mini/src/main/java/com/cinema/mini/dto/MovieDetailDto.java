package com.cinema.mini.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MovieDetailDto {
    private String movieId;
    private String title;
    private String posterPath;
    private String review;
    private double grade;
    private LocalDate releaseDate;
    private List<String> genres;

}
