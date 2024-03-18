package com.cinema.mini.service;

import com.cinema.mini.domain.Movie;
import com.cinema.mini.dto.MovieListDto;
import com.cinema.mini.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    @Override
    public List<MovieListDto> searchMovieByTitle(String title) {
        List<Movie> searchMovieList = movieRepository.findByTitleContaining(title);
        return searchMovieList.stream().map(MovieListDto::new).toList();
    }
}
