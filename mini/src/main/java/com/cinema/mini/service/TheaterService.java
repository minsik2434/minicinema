package com.cinema.mini.service;

import com.cinema.mini.dto.MovieListDto;

import java.util.List;

public interface TheaterService {
    List<MovieListDto> lastestMovieList();
    List<MovieListDto> popularMovieList();

    List<MovieListDto> genreByMovieList(String genreName);
//    List<>
}
