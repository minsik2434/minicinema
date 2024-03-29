package com.cinema.mini.repository;

import com.cinema.mini.domain.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    List<Screening> findByMovieMovieIdAndStartTimeBetween(String movieId, LocalDateTime startDateTime, LocalDateTime endDateTime);

}
