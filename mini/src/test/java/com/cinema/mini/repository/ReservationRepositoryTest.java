package com.cinema.mini.repository;

import com.cinema.mini.domain.*;
import com.cinema.mini.util.MemberUtil;
import com.cinema.mini.util.MovieUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ReservationRepositoryTest {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    MemberUtil memberUtil;
    @Autowired
    MovieUtil movieUtil;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    ScreeningRepository screeningRepository;
    @Test
    void saveTest(){
        String startTime = "2024-01-01T13:30:30";
        String endTime = "2024-01-01T15:30:30";
        Member member = memberUtil.saveMember(
                "test",
                "test@",
                "test@naver.com",
                "테스터",
                "1999-12-25",
                MemberGrade.BRONZE);
        Movie movie = movieUtil.createMovieAndMovieGenre("test", "테스트영화", "2024-01-01",
                4.5, new String[]{"액션", "드라마"});

        List<Screening> list = new ArrayList<>();
        Theater buildtheater = Theater.builder().theaterName("테스트상영관").seatCount(20).screenings(list).build();
        Theater theater = theaterRepository.save(buildtheater);
        Screening testScreening = Screening.builder().movie(movie).theater(theater)
                .startTime(LocalDateTime.parse(startTime))
                .endTime(LocalDateTime.parse(endTime))
                .build();
        Screening screening = screeningRepository.save(testScreening);
        Reservation reservation = Reservation.builder()
                .member(member)
                .screening(screening)
                .reservationDatetime(LocalDateTime.now())
                .price(15000).build();

        Reservation saveReservation = reservationRepository.save(reservation);
        Reservation findReservation = reservationRepository.findById(saveReservation.getReservationId()).orElseThrow();

        assertThat(findReservation.getMember().getMemberId()).isEqualTo(member.getMemberId());
        assertThat(findReservation.getScreening().getScreeningId()).isEqualTo(screening.getScreeningId());
    }



}