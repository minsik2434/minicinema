package com.cinema.mini.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class PaymentDto {
    @Min(15000)
    private int totalPrice;
    @NotBlank
    private Long screeningId;
    @NotBlank
    private Long[] selectedSeatIds;
    @Min(1)
    private Integer totalPersonNum;
}
