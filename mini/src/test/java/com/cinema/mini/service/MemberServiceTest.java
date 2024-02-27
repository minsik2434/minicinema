package com.cinema.mini.service;

import com.cinema.mini.domain.Member;
import com.cinema.mini.dto.LoginDto;
import com.cinema.mini.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("로그인 서비스 테스트")
    void LoginServiceTest(){
        LoginDto loginDto = new LoginDto();
        loginDto.setLoginId("test");
        loginDto.setPassword("test!");

        Member member = memberService.memberLogin(loginDto);
        assertThat(member.getMemberId()).isEqualTo(1);
    }
}
