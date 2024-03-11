package com.cinema.mini.service;

import com.cinema.mini.dto.HomeMovieListDto;

import java.util.List;

public interface TheaterService {
    List<HomeMovieListDto> lastestMovieList();
    List<HomeMovieListDto> popularMovieList();
}
