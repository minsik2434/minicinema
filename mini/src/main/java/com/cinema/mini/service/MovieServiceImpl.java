package com.cinema.mini.service;

import com.cinema.mini.domain.Movie;
import com.cinema.mini.domain.MovieGenre;
import com.cinema.mini.dto.MovieDetailDto;
import com.cinema.mini.dto.MovieDto;
import com.cinema.mini.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    @Override
    public List<MovieDto> searchMovieByTitle(String title) {
        List<Movie> searchMovieList = movieRepository.findByTitleContaining(title);
        return searchMovieList.stream().map(MovieDto::new).toList();
    }

    @Override
    public MovieDetailDto detailMovieInfo(String movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        return new MovieDetailDto(movie);
    }

    @Override
    public List<MovieDto> lastestMovieList() {
        List<Movie> lastestMovieList = movieRepository.findLastestMovieList();
        return lastestMovieList.stream().map(MovieDto::new).toList();
    }

    @Override
    public List<MovieDto> popularMovieList() {
        List<Movie> popularMovieList = movieRepository.findPopularMovieList();
        return popularMovieList.stream().map(MovieDto::new).toList();
    }

    @Override
    public List<MovieDto> genreByMovieList(String genreName) {
        Pageable pageable = PageRequest.of(0,5);
        List<Movie> genreByMovieList = movieRepository.findByMovieGenres_Genre_GenreName(genreName, pageable);
        return genreByMovieList.stream().map(MovieDto::new).toList();
    }
}
