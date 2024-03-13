package com.cinema.mini.service;

import com.cinema.mini.domain.Movie;
import com.cinema.mini.dto.MovieListDto;
import com.cinema.mini.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterServiceImpl implements TheaterService{

    final MovieRepository movieRepository;

    @Override
    public List<MovieListDto> lastestMovieList() {
        List<Movie> lastestMovieList = movieRepository.findLastestMovieList();
        return lastestMovieList.stream().map(MovieListDto::new).toList();
    }

    @Override
    public List<MovieListDto> popularMovieList() {
        List<Movie> popularMovieList = movieRepository.findPopularMovieList();
        return popularMovieList.stream().map(MovieListDto::new).toList();
    }

    @Override
    public List<MovieListDto> genreByMovieList(String genreName) {
        Pageable pageable = PageRequest.of(0,5);
        List<Movie> genreByMovieList = movieRepository.findByMovieGenres_Genre_GenreName(genreName, pageable);
        return genreByMovieList.stream().map(MovieListDto::new).toList();
    }
}
