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
        //테스트 상영관 저장
        Theater saveTheater = theaterRepository.save(getTheater("1관", 30));

        //테스트 영화 저장
        Movie saveMovie = movieUtil.createMovieAndMovieGenre("test", "테스트1", "2024-01-01",
                4.5, new String[]{"액션", "스릴러"});
        //상영 테이블 저장
        Screening testScreening = Screening.builder().movie(saveMovie).theater(saveTheater)
                .startTime(LocalDateTime.parse("2024-01-01T15:30:00"))
                .endTime(LocalDateTime.parse("2024-01-01T16:30:00"))
                .build();

        saveMovie.getScreenings().add(testScreening);
        saveTheater.getScreenings().add(testScreening);
        Screening save = screeningRepository.save(testScreening);

        Movie findMovie = movieRepository.findById("test").orElseThrow();
        assertThat(findMovie).isEqualTo(save.getMovie());
    }

    @Test
    void findByMovieMovieIdAndStartTimeBetweenTest(){
        LocalDate startDate = LocalDate.parse("2024-01-01");
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = startDate.plusDays(1).atStartOfDay();
        List<Screening> screenings = screeningRepository.findByMovieMovieIdAndStartTimeBetween("test01", startDateTime, endDateTime);

//        Map<Long, List<Screening>> groupedByTheater = screenings.stream().collect(Collectors.groupingBy(screening -> screening.getTheater().getTheaterId()));

    }


    private Theater getTheater(String theaterName, int seatCount) {
        List<Screening> list = new ArrayList<>();
        return Theater.builder().theaterName(theaterName).seatCount(seatCount).screenings(list).build();
    }
}
