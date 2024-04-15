package com.cinema.mini.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class SeatDto {
    private Long seatId;
    private String seatRow;
    private int seatNumber;
    private boolean reserved;
}
