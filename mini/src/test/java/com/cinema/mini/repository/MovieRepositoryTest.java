package com.cinema.mini.repository;

import com.cinema.mini.domain.Genre;
import com.cinema.mini.domain.Movie;
import com.cinema.mini.domain.MovieGenre;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    GenreRepository genreRepository;

    @Test
    void findLastestMovieList(){
        List<Movie> list = movieRepository.findLastestMovieList();
        List<String> titles = new ArrayList<>();
        titles.add("쿵푸팬더 4");
        titles.add("Megamind vs. the Doom Syndicate");
        titles.add("듄: 파트 2");
        titles.add("코드 8: 파트 2");
        titles.add("우주인");
        int idx = 0;
        for (Movie movie : list) {
            assertThat(movie.getTitle()).isEqualTo(titles.get(idx++));
            log.info(movie.getTitle());
        }
    }

    @Test
    void findPopularMovieList(){
        List<Movie> list = movieRepository.findPopularMovieList();
        List<String> titles = new ArrayList<>();
        titles.add("듄: 파트 2");
        titles.add("가여운 것들");
        titles.add("듄");
        titles.add("인투 더 월드");
        titles.add("비키퍼");
        int idx = 0;
        for (Movie movie : list) {
            assertThat(movie.getTitle()).isEqualTo(titles.get(idx++));
        }
    }

    @Test
    void findRomanceMovieList(){
        Pageable pageable = PageRequest.of(0,5);
        List<Movie> list = movieRepository.findByMovieGenres_Genre_GenreName("로맨스", pageable);
        Genre genre = genreRepository.findByGenreName("로맨스").orElseThrow();
        list.forEach(movie -> {
            boolean containsRomance = movie.getMovieGenres().stream()
                    .anyMatch(movieGenre -> movieGenre.getGenre().equals(genre));
            assertThat(containsRomance).isTrue();
        });
    }

    @Test
    void findByTitleIsLikeTest(){
        List<Movie> list = movieRepository.findByTitleContaining("가");
        List<String> nameList = new ArrayList<>();
        nameList.add("가여운 것들");
        nameList.add("아가일");
        list.forEach(movie -> {
            boolean containMovie = nameList.contains(movie.getTitle());
            assertThat(containMovie).isTrue();
        });
    }

}

