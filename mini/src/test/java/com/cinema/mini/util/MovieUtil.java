package com.cinema.mini.util;

import com.cinema.mini.domain.Genre;
import com.cinema.mini.domain.Movie;
import com.cinema.mini.domain.MovieGenre;
import com.cinema.mini.domain.Screening;
import com.cinema.mini.repository.GenreRepository;
import com.cinema.mini.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class MovieUtil {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    GenreRepository genreRepository;
    public Movie createTestMovie(String movieId, String movieTitle, String localDate, double grade) {
        List<MovieGenre> movieGenres = new ArrayList<>();
        List<Screening> screenings = new ArrayList<>();
        return Movie.builder()
                .movieId(movieId)
                .title(movieTitle)
                .adult(true)
                .posterPath("testPosterPath")
                .releaseDate(LocalDate.parse(localDate))
                .overview("테스트 영화")
                .grade(grade)
                .movieGenres(movieGenres)
                .screenings(screenings)
                .build();
    }

    @Transactional
    public Movie createMovieAndMovieGenre(String movieId, String title, String releaseDate, double grade, String[] genreNames) {
        Movie movie = createTestMovie(movieId, title, releaseDate, grade);
        for (String genreName : genreNames) {
            MovieGenre movieGenre = getMovieGenre(genreName, movie);
            movie.getMovieGenres().add(movieGenre);
        }
        return movieRepository.save(movie);
    }

    public MovieGenre getMovieGenre(String genreName, Movie movie) {
        Genre genre = genreRepository.findByGenreName(genreName).orElseThrow();
        return MovieGenre.builder().movie(movie).genre(genre).build();
    }
}
