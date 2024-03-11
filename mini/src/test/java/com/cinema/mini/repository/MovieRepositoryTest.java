package com.cinema.mini.repository;

import com.cinema.mini.domain.Movie;
import com.cinema.mini.dto.HomeMovieListDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.PATH;

@SpringBootTest
@Slf4j
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;

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

}

