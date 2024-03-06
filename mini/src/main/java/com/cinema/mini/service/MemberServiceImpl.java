package com.cinema.mini.service;

import com.cinema.mini.domain.Member;
import com.cinema.mini.dto.LoginDto;
import com.cinema.mini.dto.MemberRegisterDto;
import com.cinema.mini.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    final MemberRepository memberRepository;
    public static final String BRONZE = "Bronze";

    @Override
    @Transactional
    public void memberRegister(MemberRegisterDto memberRegisterDto) {

        LocalDate memberBirth = getMemberBirth(memberRegisterDto);
        Member member = Member.builder()
                .loginId(memberRegisterDto.getLoginId())
                .password(memberRegisterDto.getPassword())
                .email(memberRegisterDto.getEmail())
                .name(memberRegisterDto.getName())
                .birth(memberBirth)
                .grade(BRONZE).build();
        memberRepository.save(member);
    }

    private LocalDate getMemberBirth(MemberRegisterDto memberRegisterDto) {
        String birthString = memberRegisterDto.getBirthYear() +"-"+ memberRegisterDto.getBirthMonth() +"-"+
                memberRegisterDto.getBirthDay();
        return LocalDate.parse(birthString);
    }

    @Override
    public Member memberLogin(LoginDto loginDto) {
        return memberRepository.findByLoginId(loginDto.getLoginId())
                .filter(m -> m.getPassword().equals(loginDto.getPassword())).orElse(null);
    }

    @Override
    public boolean isDuplicateMemberId(String userId) {
        return memberRepository.findByLoginId(userId).isPresent();
    }
}
