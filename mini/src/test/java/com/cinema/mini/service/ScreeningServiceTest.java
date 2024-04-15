package com.cinema.mini.service;

import com.cinema.mini.domain.*;
import com.cinema.mini.dto.SeatDto;
import com.cinema.mini.repository.*;
import com.cinema.mini.util.MemberUtil;
import com.cinema.mini.util.MovieUtil;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
public class ScreeningServiceTest {

    @Autowired
    ScreeningRepository screeningRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ReservedSeatRepository reservedSeatRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    MovieUtil movieUtil;
    @Autowired
    MemberUtil memberUtil;
    @Test
    @Transactional
    void SeatInfo(){
        String theaterName = "10관";
        String startTime = "2024-01-01T13:30:30";
        String endTime = "2024-01-01T15:30:30";
        Movie movie = movieUtil.createMovieAndMovieGenre("test",
                "테스트영화",
                "2024-01-01",
                4.5,
                new String[]{"액션", "드라마"});
        Theater saveTheater = theaterRepository.save(getTheater(theaterName, 30));
        Seat s1 = Seat.builder().seatNumber(1).seatRow("A").theater(saveTheater).build();
        Seat s2 = Seat.builder().seatNumber(2).seatRow("A").theater(saveTheater).build();
        seatRepository.save(s1);
        seatRepository.save(s2);
        saveTheater.getSeats().add(s1);
        saveTheater.getSeats().add(s2);

        Screening screening = Screening.builder()
                .movie(movie)
                .theater(saveTheater)
                .startTime(LocalDateTime.parse(startTime))
                .endTime(LocalDateTime.parse(endTime))
                .build();
        Screening saveScreening = screeningRepository.save(screening);

        Member member = memberUtil.saveMember("test", "test!", "test@gmail.com", "테스터", "1999-12-25", MemberGrade.BRONZE);

        //예약된 좌석
        Reservation reservation = Reservation.builder()
                .screening(saveScreening)
                .member(member)
                .reservationDatetime(LocalDateTime.now())
                .price(15000)
                .build();
        Reservation saveReservation = reservationRepository.save(reservation);

        ReservedSeat reservedSeat = ReservedSeat.builder().reservation(saveReservation).seat(s1).build();
        reservedSeatRepository.save(reservedSeat);


        Screening findScreening = screeningRepository.findById(saveScreening.getScreeningId()).orElseThrow();
        List<Seat> seats = findScreening.getTheater().getSeats();
        List<Seat> seatsByScreeningId = reservedSeatRepository.findSeatsByScreeningId(findScreening.getScreeningId());
        List<SeatDto> seatDtoList = seats.stream().map(seat -> {
            SeatDto seatDto = new SeatDto();
            seatDto.setSeatId(seat.getSeatId());
            seatDto.setSeatRow(seat.getSeatRow());
            seatDto.setSeatNumber(seat.getSeatNumber());
            seatDto.setReserved(seatsByScreeningId.contains(seat));
            return seatDto;
        }).toList();

        seatDtoList.forEach(seatDto -> {
            if(Objects.equals(seatDto.getSeatId(), s1.getSeatId())){
                assertThat(seatDto.isReserved()).isTrue();
            }
            else if(Objects.equals(seatDto.getSeatId(), s2.getSeatId())){
                assertThat(seatDto.isReserved()).isFalse();
            }
        });

    }

    private Theater getTheater(String theaterName, int seatCount) {
        List<Screening> list = new ArrayList<>();
        List<Seat> seatList = new ArrayList<>();
        return Theater.builder().theaterName(theaterName).seatCount(seatCount).screenings(list).seats(seatList).build();
    }
}
