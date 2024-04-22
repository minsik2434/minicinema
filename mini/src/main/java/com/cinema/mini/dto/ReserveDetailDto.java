package com.cinema.mini.dto;

import com.cinema.mini.domain.Reservation;
import com.cinema.mini.domain.Screening;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ReserveDetailDto {
    private Long reservationId;
    private ScreeningInfoDto screeningInfoDto;
    private List<String> reserveSeats = new ArrayList<>();
    private int originPrice;
    private int discountPrice;
    private int totalPrice;

    public ReserveDetailDto(Reservation reservation){
        this.reservationId = reservation.getReservationId();
        this.screeningInfoDto = new ScreeningInfoDto(reservation.getScreening());
        this.totalPrice = reservation.getTotalPrice();
        this.discountPrice = reservation.getDiscountPrice();
        this.originPrice = reservation.getOriginPrice();
    }

    @Data
    static class ScreeningInfoDto{
        private String posterPath;
        private String movieTitle;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private String theaterName;

        ScreeningInfoDto(Screening screening){
            this.posterPath = screening.getMovie().getPosterPath();
            this.movieTitle = screening.getMovie().getTitle();
            this.startTime = screening.getStartTime();
            this.endTime = screening.getEndTime();
            this.theaterName = screening.getTheater().getTheaterName();
        }
    }

}
