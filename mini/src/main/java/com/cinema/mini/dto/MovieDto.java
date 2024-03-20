package com.cinema.mini.dto;

import com.cinema.mini.domain.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieDto {
    private String movieId;
    private String title;
    private String posterPath;
    private double grade;

    public MovieDto(Movie movie){
        this.movieId = movie.getMovieId();
        this.title = movie.getTitle();
        this.posterPath = movie.getPosterPath();
        this.grade = movie.getGrade();
    }
}
