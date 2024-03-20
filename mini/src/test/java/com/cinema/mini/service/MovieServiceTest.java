package com.cinema.mini.service;

import com.cinema.mini.domain.Movie;
import com.cinema.mini.dto.MovieDto;
import com.cinema.mini.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
public class MovieServiceTest {

    @Autowired
    MovieService movieService;
    @Autowired
    MovieRepository movieRepository;

    @Test
    @Transactional
    void searchMovieByTitleTest(){
        List<Movie> createMovieList = new ArrayList<>();
        createMovieList.add(createTestMovie("test02", "test_2"));
        createMovieList.add(createTestMovie("test01", "test_1"));
        createMovieList.add(createTestMovie("test03", "test_3"));
        movieRepository.saveAll(createMovieList);
        List<MovieDto> list = movieService.searchMovieByTitle("test");
        assertThat(list.size()).isEqualTo(3);
        createMovieList.forEach(expectMovie ->{
            boolean isContain = list.stream().anyMatch(
                    findMovie -> expectMovie.getTitle().equals(findMovie.getTitle())
            );
            assertThat(isContain).isTrue();
        });
    }

    @Test
    void latestMovieListTest(){
        List<MovieDto> lastestMovieDtos = movieService.lastestMovieList();
        List<String> titles = new ArrayList<>();
        titles.add("쿵푸팬더 4");
        titles.add("Megamind vs. the Doom Syndicate");
        titles.add("듄: 파트 2");
        titles.add("코드 8: 파트 2");
        titles.add("우주인");
        int idx = 0;
        for (MovieDto lastestMovieDto : lastestMovieDtos) {
            assertThat(lastestMovieDto.getTitle()).isEqualTo(titles.get(idx++));
        }
    }


    private Movie createTestMovie(String testMovieId, String testMovieTitle) {
        return Movie.builder()
                .movieId(testMovieId)
                .title(testMovieTitle)
                .adult(true)
                .posterPath("testPosterPath")
                .releaseDate(LocalDate.parse("2020-12-11"))
                .overview("테스트 영화")
                .grade(4.5).build();
    }
}
