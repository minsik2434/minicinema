package com.cinema.mini.repository;

import com.cinema.mini.domain.ReservedSeat;
import com.cinema.mini.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservedSeatRepository extends JpaRepository<ReservedSeat, Long> {
    @Query("SELECT rs.seat FROM ReservedSeat rs WHERE rs.reservation.screening.screeningId = :screeningId")
    List<Seat> findReservedSeatsByScreeningId(Long screeningId);

    @Query("SELECT COUNT(rs) FROM ReservedSeat rs join rs.reservation r where r.screening.screeningId = :screeningId")
    int reservedSeatCount(@Param("screeningId") Long screeningId);
}
