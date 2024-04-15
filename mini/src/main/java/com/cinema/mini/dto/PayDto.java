package com.cinema.mini.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayDto {
    @NotBlank
    private Long screeningId;
    @Min(1)
    @Max(4)
    private String totalPersonNum;
    @NotBlank
    private Long[] selectedSeats;
}
