package com.cinema.mini.service;

import com.cinema.mini.dto.MovieDetailDto;
import com.cinema.mini.dto.MovieDto;

import java.util.List;

public interface MovieService {
    List<MovieDto> searchMovieByTitle(String title);
    MovieDetailDto detailMovieInfo(String movieId);

    List<MovieDto> lastestMovieList();
    List<MovieDto> popularMovieList();
    List<MovieDto> genreByMovieList(String genreName);
    List<MovieDto> playingMovie();
}
