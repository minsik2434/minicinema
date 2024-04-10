package com.cinema.mini.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long theaterId;

    private String theaterName;
    private int seatCount;

    @OneToMany(mappedBy = "theater")
    private List<Screening> screenings;

    @OneToMany(mappedBy = "theater")
    private List<Seat> seats;
}
