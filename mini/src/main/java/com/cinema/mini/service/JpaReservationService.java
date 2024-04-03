package com.cinema.mini.service;

import com.cinema.mini.domain.Screening;
import com.cinema.mini.dto.MovieAndScreeningDto;
import com.cinema.mini.dto.ScreeningDto;
import com.cinema.mini.repository.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class JpaReservationService implements ReservationService{
    private final ScreeningRepository screeningRepository;
    @Override
    public List<ScreeningDto> getScreeningInfoForTheater(String movieId, String selectedDate) {
        log.info("movieId={}",movieId);
        log.info("selectedDate={}",selectedDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate startDate = LocalDate.parse(selectedDate,formatter);
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = startDate.plusDays(1).atStartOfDay();
        List<Screening> findScreenings = screeningRepository.findByMovieMovieIdAndStartTimeBetween(movieId, startDateTime, endDateTime).orElseThrow();

        Map<Long, List<Screening>> groupedByTheater = findScreenings.stream().collect(Collectors.groupingBy(
                screening -> screening.getTheater().getTheaterId()
        ));

        return groupedByTheater.entrySet().stream().map(entry ->{
            ScreeningDto screeningDto = new ScreeningDto();
            screeningDto.setTheaterId(entry.getKey());
            screeningDto.setTheaterName(entry.getValue().get(0).getTheater().getTheaterName());
            List<ScreeningDto.ScreeningByTheater> screenings = entry.getValue().stream().map(screening -> {
                ScreeningDto.ScreeningByTheater screeningByTheater = new ScreeningDto.ScreeningByTheater();
                screeningByTheater.setScreeningId(screening.getScreeningId());
                screeningByTheater.setStartTime(screening.getStartTime());
                screeningByTheater.setEndTime(screening.getEndTime());
                screeningByTheater.setMaxSeat(screening.getTheater().getSeatCount());
                //TODO 남은 좌석은 예약 좌석 로직 구현후 구현예정
                screeningByTheater.setRemainSeat(0);
                return screeningByTheater;
            }).toList();
            screeningDto.setScreenings(screenings);
            return screeningDto;
        }).toList();
    }

    @Override
    public MovieAndScreeningDto getScreeningInfoForScreeningId(Long screeningId) {
        Screening screening = screeningRepository.findByScreeningId(screeningId).orElseThrow();
        return new MovieAndScreeningDto(screening);
    }
}
