package com.cinema.mini.service;

import com.cinema.mini.domain.Member;
import com.cinema.mini.dto.LoginDto;
import com.cinema.mini.dto.MemberRegisterDto;
import com.cinema.mini.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
@Slf4j
@SpringBootTest
public class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("로그인 서비스 테스트")
    void memberLogin(){
        LoginDto loginDto = new LoginDto();
        loginDto.setLoginId("test");
        loginDto.setPassword("test!");

        Member member = memberService.memberLogin(loginDto);
        assertThat(member.getMemberId()).isEqualTo(1);
    }

    @Test
    @DisplayName("회원 저장 테스트")
    @Transactional
    void memberRegisterTest(){
        MemberRegisterDto memberRegisterDto = new MemberRegisterDto();
        memberRegisterDto.setLoginId("test123");
        memberRegisterDto.setPassword("test123");
        memberRegisterDto.setRePassword("test123");
        memberRegisterDto.setEmail("test123@naver.com");
        memberRegisterDto.setName("민식");
        memberRegisterDto.setBirthYear("1999");
        memberRegisterDto.setBirthMonth("12");
        memberRegisterDto.setBirthDay("25");
        memberService.memberRegister(memberRegisterDto);
        Optional<Member> registerMember = memberRepository.findByLoginId("test123");
        assertThat(registerMember).isPresent();
    }

    @Test
    @DisplayName("입력받은 회원 생년월일 Date 형변환 테스트")
    void getMemberBirth(){
        MemberRegisterDto memberRegisterDto = new MemberRegisterDto();
        memberRegisterDto.setBirthYear("1999");
        memberRegisterDto.setBirthMonth("12");
        memberRegisterDto.setBirthDay("25");
        Date memberBirth = getMemberBirth(memberRegisterDto);
        assertThat(memberBirth).hasYear(1999).hasMonth(12).hasDayOfMonth(25);
    }

    @Test
    @DisplayName("중복 아이디 체크 테스트")
    void isDuplicateMemberId(){
        boolean check = memberService.isDuplicateMemberId("test");
        assertThat(check).isEqualTo(true);
    }

    private Date getMemberBirth(MemberRegisterDto memberRegisterDto) {
        String birthString = memberRegisterDto.getBirthYear() + memberRegisterDto.getBirthMonth()
                + memberRegisterDto.getBirthDay();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            return sdf.parse(birthString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}


