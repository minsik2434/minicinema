package com.cinema.mini.domain;

import com.cinema.mini.repository.GenreRepository;
import com.cinema.mini.repository.MovieRepository;
import com.cinema.mini.util.MovieUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
public class MovieDomainTest {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    MovieUtil util;
    @Test
    void movieReferenceTest(){

        util.createMovieAndMovieGenre("test","테스트","2024-01-01", 4.5,
                new String[]{"액션", "드라마"});

        List<Genre> genres = new ArrayList<>();
        Movie movie = movieRepository.findById("test").orElseThrow();
        List<MovieGenre> movieGenres = movie.getMovieGenres();
        genres.add(genreRepository.findById("28").orElseThrow());
        genres.add(genreRepository.findById("18").orElseThrow());
        for (int i=0; i<genres.size(); i++) {
            assertThat(genres).contains(movieGenres.get(i).getGenre());
        }
    }
}
