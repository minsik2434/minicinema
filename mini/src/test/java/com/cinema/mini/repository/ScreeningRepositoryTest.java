package com.cinema.mini.repository;

import com.cinema.mini.domain.Movie;
import com.cinema.mini.domain.Screening;
import com.cinema.mini.domain.Theater;
import com.cinema.mini.util.MovieUtil;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
public class ScreeningRepositoryTest {

    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ScreeningRepository screeningRepository;
    @Autowired
    MovieUtil movieUtil;

    @Test
    void saveTest(){
        String startTime = "2024-01-01T13:30:30";
        String endTime = "2024-01-01T15:30:30";
        //테스트 상영관 저장
        Theater saveTheater = theaterRepository.save(getTheater("10관", 30));

        //테스트 영화 저장
        Movie saveMovie = movieUtil.createMovieAndMovieGenre("test", "테스트1", "2024-01-01",
                4.5, new String[]{"액션", "스릴러"});
        //상영 테이블 저장
        Screening testScreening = getScreening(saveMovie, saveTheater, startTime, endTime);

        saveMovie.getScreenings().add(testScreening);
        saveTheater.getScreenings().add(testScreening);
        Screening save = screeningRepository.save(testScreening);

        Movie findMovie = movieRepository.findById("test").orElseThrow();
        assertThat(findMovie).isEqualTo(save.getMovie());
    }

    @Test
    void findByMovieMovieIdAndStartTimeBetweenTest(){

        String movieId = "test";
        String theaterName = "10관";
        String startTime = "2024-01-01T13:30:30";
        String endTime = "2024-01-01T15:30:30";

        Theater saveTheater = theaterRepository.save(getTheater(theaterName, 30));
        Movie saveMovie = movieUtil.createMovieAndMovieGenre(movieId, "테스트1", "2024-01-01",
                4.5, new String[]{"액션", "스릴러"});

        Screening testScreening = getScreening(saveMovie, saveTheater, startTime, endTime);
        screeningRepository.save(testScreening);

        LocalDate startDate = LocalDate.parse("2024-01-01");
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = startDate.plusDays(1).atStartOfDay();
        List<Screening> screenings = screeningRepository.findByMovieMovieIdAndStartTimeBetween(
                movieId, startDateTime, endDateTime).orElseThrow();

        assertThat(screenings).isNotEmpty();

        screenings.forEach(screening -> {
            assertThat(screening.getMovie().getMovieId()).isEqualTo(movieId);
            assertThat(screening.getTheater().getTheaterName()).isEqualTo(theaterName);
            assertThat(screening.getStartTime().toLocalDate()).isEqualTo(startDate);
        });
    }


    @Test
    void dateFormatterTest(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate localDate1 = LocalDate.parse("2024-10-3",formatter);
        LocalDate localDate2 = LocalDate.parse("2024-9-3",formatter);
        LocalDate localDate3 = LocalDate.parse("2024-12-12",formatter);
        log.info("localDate1={}",localDate1);
        log.info("localDate2={}",localDate2);
        log.info("localDate3={}",localDate3);
    }


    private Theater getTheater(String theaterName, int seatCount) {
        List<Screening> list = new ArrayList<>();
        return Theater.builder().theaterName(theaterName).seatCount(seatCount).screenings(list).build();
    }

    private Screening getScreening(Movie movie, Theater theater, String startTime, String endTime) {
        return Screening.builder().movie(movie).theater(theater)
                .startTime(LocalDateTime.parse(startTime))
                .endTime(LocalDateTime.parse(endTime))
                .build();
    }

}
