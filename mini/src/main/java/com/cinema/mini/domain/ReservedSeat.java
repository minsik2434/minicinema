package com.cinema.mini.domain;

import jakarta.persistence.*;

@Entity
public class ReservedSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservedSeatId;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

}
