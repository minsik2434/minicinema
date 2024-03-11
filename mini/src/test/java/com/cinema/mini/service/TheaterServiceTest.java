package com.cinema.mini.service;

import com.cinema.mini.dto.HomeMovieListDto;
import com.cinema.mini.repository.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TheaterServiceTest {

    @Autowired
    TheaterService theaterService;
    @Test
    void latestMovieList(){
        List<HomeMovieListDto> lastestMovieDtos = theaterService.lastestMovieList();
        List<String> titles = new ArrayList<>();
        titles.add("쿵푸팬더 4");
        titles.add("Megamind vs. the Doom Syndicate");
        titles.add("듄: 파트 2");
        titles.add("코드 8: 파트 2");
        titles.add("우주인");
        int idx = 0;
        for (HomeMovieListDto lastestMovieDto : lastestMovieDtos) {
            assertThat(lastestMovieDto.getTitle()).isEqualTo(titles.get(idx++));
        }

    }
}
