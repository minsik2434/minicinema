package com.cinema.mini.repository;

import com.cinema.mini.domain.*;
import com.cinema.mini.util.MemberUtil;
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

@SpringBootTest
@Slf4j
public class ReservedSeatRepositoryTest {

    @Autowired
    ReservedSeatRepository reservedSeatRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    MovieUtil movieUtil;
    @Autowired
    ScreeningRepository screeningRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    MemberUtil memberUtil;
    @Autowired
    ReservationRepository reservationRepository;

    @Test
    @Transactional
    void  reservedSeatCountTest(){
        String startTime = "2024-01-01T13:30:30";
        String endTime = "2024-01-01T15:30:30";
        List<Seat> seatList = new ArrayList<>();
        //영화
        Movie movie = movieUtil.createMovieAndMovieGenre("test",
                "테스트 영화",
                "2024-01-01",
                4.5, new String[]{"액션"});
        //상영관
        Theater theater = getTheater("9관", 10);
        Theater saveTheater = theaterRepository.save(theater);

        //좌석
        for(int i=0; i<10; i++){
            Seat seat = Seat.builder().seatRow("A").seatNumber(i).build();
            seatList.add(seat);
        }
        seatRepository.saveAll(seatList);

        //상영
        Screening screening = getScreening(movie, saveTheater, startTime, endTime);
        Screening saveScreening = screeningRepository.save(screening);

        //회원
        Member member = memberUtil.saveMember("test",
                "test@",
                "test@naver.com",
                "테스터",
                "1999-12-24",
                MemberGrade.BRONZE);

        Reservation reservation = Reservation.builder().member(member)
                .screening(screening)
                .reservationDatetime(LocalDateTime.now())
                .price(10000)
                .reservedSeatList(new ArrayList<>()).build();

        Reservation saveReservation = reservationRepository.save(reservation);
        Seat seat1 = seatRepository.findById(seatList.get(0).getSeatId()).orElseThrow();
        Seat seat2 = seatRepository.findById(seatList.get(1).getSeatId()).orElseThrow();
        ReservedSeat reservedSeat = ReservedSeat.builder().reservation(saveReservation).seat(seat1).build();
        ReservedSeat reservedSeat2 = ReservedSeat.builder().reservation(saveReservation).seat(seat1).build();
        reservedSeatRepository.save(reservedSeat);
        reservedSeatRepository.save(reservedSeat2);
        int count = reservedSeatRepository.reservedSeatCount(saveScreening.getScreeningId());
        Assertions.assertThat(count).isEqualTo(2);

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
