package com.cinema.mini.domain;

import jakarta.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    private int seatNumber;
    private int seatRow;

}
