package com.cinema.mini.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PayInfoDto {
    private Integer screeningId;
    private List<String> selectedSeat;
    private Integer totalPersonNum;
}
