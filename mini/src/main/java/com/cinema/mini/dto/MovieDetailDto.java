package com.cinema.mini.dto;

import com.cinema.mini.domain.Movie;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class MovieDetailDto {
    private String movieId;
    private String title;
    private String posterPath;
    private String overview;
    private double grade;
    private LocalDate releaseDate;
    private List<String> genres;

    public MovieDetailDto(){
    }

    public MovieDetailDto(Movie movie){
        this.movieId = movie.getMovieId();
        this.title = movie.getTitle();
        this.posterPath = movie.getPosterPath();
        this.overview = movie.getOverview();
        this.grade = movie.getGrade();
        this.releaseDate = movie.getReleaseDate();
        this.genres = movie.getMovieGenres().stream().map(movieGenre -> movieGenre.getGenre().getGenreName()).toList();
    }
}
