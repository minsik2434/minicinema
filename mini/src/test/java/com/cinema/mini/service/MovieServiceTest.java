package com.cinema.mini.service;

import com.cinema.mini.domain.Movie;
import com.cinema.mini.domain.MovieGenre;
import com.cinema.mini.domain.Screening;
import com.cinema.mini.dto.MovieDetailDto;
import com.cinema.mini.dto.MovieDto;
import com.cinema.mini.repository.MovieRepository;
import com.cinema.mini.util.MovieUtil;
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
@Transactional
public class MovieServiceTest {

    @Autowired
    MovieService movieService;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieUtil movieUtil;

    @Test
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
    void detailMovieInfoTest(){
        Movie movie = movieUtil.createMovieAndMovieGenre("test01", "테스트1",
                "2024-03-01", 4.5, new String[]{"액션", "가족","모험"});
        MovieDetailDto movieDetailDto = movieService.detailMovieInfo("test01");

        assertThat(movieDetailDto).isNotNull();

        assertThat(movie.getMovieId()).isEqualTo(movieDetailDto.getMovieId());
        assertThat(movie.getTitle()).isEqualTo(movieDetailDto.getTitle());
        assertThat(movie.getPosterPath()).isEqualTo(movieDetailDto.getPosterPath());
        assertThat(movie.getOverview()).isEqualTo(movieDetailDto.getOverview());
        assertThat(movie.getGrade()).isEqualTo(movieDetailDto.getGrade());
        assertThat(movie.getReleaseDate()).isEqualTo(movieDetailDto.getReleaseDate());
        assertThat(movieDetailDto.getGenres())
                .containsExactlyInAnyOrderElementsOf(movie.getMovieGenres()
                        .stream()
                        .map(movieGenre -> movieGenre.getGenre().getGenreName()).toList());
    }


    private Movie createTestMovie(String testMovieId, String testMovieTitle) {
        List<MovieGenre> movieGenres = new ArrayList<>();
        List<Screening> screenings = new ArrayList<>();
        return Movie.builder()
                .movieId(testMovieId)
                .title(testMovieTitle)
                .adult(true)
                .posterPath("testPosterPath")
                .releaseDate(LocalDate.parse("2020-12-11"))
                .overview("테스트 영화")
                .movieGenres(movieGenres)
                .screenings(screenings)
                .grade(4.5).build();
    }
}
