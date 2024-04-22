package com.cinema.mini.service;

import com.cinema.mini.domain.Member;
import com.cinema.mini.dto.ReserveDetailDto;

import java.util.List;

public interface MyPageService {
    List<ReserveDetailDto> reservationDetail(Member member);
}
