package com.cinema.mini.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class PaymentDto {
    private int totalPrice;
    private int originPrice;
    private int discountPrice;
    @NotBlank
    private Long screeningId;
    @NotBlank
    private Long[] selectedSeatIds;
    @Min(1)
    private Integer totalPersonNum;
}
