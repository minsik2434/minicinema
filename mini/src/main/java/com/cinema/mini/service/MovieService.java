package com.cinema.mini.service;

import com.cinema.mini.dto.MovieListDto;

import java.util.List;

public interface MovieService {
    List<MovieListDto> searchMovieByTitle(String title);
}
