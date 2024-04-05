package com.cinema.mini.repository;

import com.cinema.mini.domain.Member;
import com.cinema.mini.domain.MemberGrade;
import com.cinema.mini.util.MemberUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationRepositoryTest {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    MemberUtil memberUtil;
    @Test
    void saveTest(){
        memberUtil.saveMember(
                "test",
                "test@",
                "test@naver.com",
                "테스터",
                "1999-12-25",
                MemberGrade.BRONZE);
    }

}