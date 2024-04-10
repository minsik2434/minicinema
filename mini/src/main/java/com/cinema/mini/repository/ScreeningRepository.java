package com.cinema.mini.repository;

import com.cinema.mini.domain.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    Optional<List<Screening>> findByMovieMovieIdAndStartTimeBetween(String movieId, LocalDateTime startDateTime, LocalDateTime endDateTime);
//    Optional<Screening> findByScreeningId(Long screeningId);
}
