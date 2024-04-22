package com.cinema.mini.service;

import com.cinema.mini.domain.Member;
import com.cinema.mini.domain.Reservation;
import com.cinema.mini.domain.ReservedSeat;
import com.cinema.mini.domain.Seat;
import com.cinema.mini.dto.ReserveDetailDto;
import com.cinema.mini.repository.ReservationRepository;
import com.cinema.mini.repository.ReservedSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JpaMyPageService implements MyPageService{

    private final ReservationRepository reservationRepository;

    @Override
    public List<ReserveDetailDto> reservationDetail(Member member) {
        List<Reservation> reservations = reservationRepository.findByMember(member);
        return reservations.stream().map(reservation -> {
            ReserveDetailDto reserveDetailDto = new ReserveDetailDto(reservation);
            List<ReservedSeat> seats = reservation.getReservedSeatList();
            reserveDetailDto.getReserveSeats().addAll(seats.stream()
                    .map(reservedSeat -> {
                        Seat seat = reservedSeat.getSeat();
                        return seat.getSeatRow() + seat.getSeatNumber();
                    }).toList());
            return reserveDetailDto;
        }).toList();
    }
}
