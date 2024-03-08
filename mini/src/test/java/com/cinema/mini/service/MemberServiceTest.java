package com.cinema.mini.service;

import com.cinema.mini.domain.Member;
import com.cinema.mini.dto.LoginDto;
import com.cinema.mini.dto.MemberRegisterDto;
import com.cinema.mini.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
@Slf4j
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("로그인 서비스 테스트")
    void memberLoginTest(){
        Member saveMember = saveTestMember();
        LoginDto loginDto = new LoginDto("test","test!");
        Member member = memberService.memberLogin(loginDto);
        assertThat(member).isEqualTo(saveMember);
    }

    @Test
    @DisplayName("회원 저장 테스트")
    void memberRegisterTest(){
        MemberRegisterDto memberRegisterDto = new MemberRegisterDto();
        memberRegisterDto.setLoginId("test");
        memberRegisterDto.setPassword("test!");
        memberRegisterDto.setRePassword("test!");
        memberRegisterDto.setEmail("test@naver.com");
        memberRegisterDto.setName("최민식");
        memberRegisterDto.setBirthYear("1999");
        memberRegisterDto.setBirthMonth("12");
        memberRegisterDto.setBirthDay("25");
        memberService.memberRegister(memberRegisterDto);
        Optional<Member> registerMember = memberRepository.findByLoginId("test123");
        assertThat(registerMember).isPresent();
    }
    @Test
    @DisplayName("중복 아이디 체크 테스트")
    void isDuplicateMemberIdTest(){
        saveTestMember();
        boolean check = memberService.isDuplicateMemberId("test");
        log.info("check={}",check);
        assertThat(check).isEqualTo(true);
    }

    private Member saveTestMember() {
        LocalDate localDate = LocalDate.parse("1999-12-25");
        Member newMember = Member.builder()
                .loginId("test")
                .password("test!")
                .email("test@naver.com")
                .name("테스터")
                .birth(localDate)
                .grade("Bronze").build();
        return memberRepository.save(newMember);
    }
}


