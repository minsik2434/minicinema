package com.cinema.mini.service;

import com.cinema.mini.domain.Movie;
import com.cinema.mini.dto.HomeMovieListDto;
import com.cinema.mini.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterServiceImpl implements TheaterService{

    final MovieRepository movieRepository;

    @Override
    public List<HomeMovieListDto> lastestMovieList() {
        List<Movie> lastestMovieList = movieRepository.findLastestMovieList();
        return lastestMovieList.stream().map(HomeMovieListDto::new).toList();
    }

    @Override
    public List<HomeMovieListDto> popularMovieList() {
        List<Movie> popularMovieList = movieRepository.findPopularMovieList();
        return popularMovieList.stream().map(HomeMovieListDto::new).toList();
    }
}
