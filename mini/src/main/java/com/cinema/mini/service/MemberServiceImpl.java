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

        Date memberBirth = getMemberBirth(memberRegisterDto);
        Member member = Member.builder()
                .loginId(memberRegisterDto.getLoginId())
                .password(memberRegisterDto.getPassword())
                .email(memberRegisterDto.getEmail())
                .name(memberRegisterDto.getName())
                .birth(memberBirth)
                .grade(BRONZE).build();
        memberRepository.save(member);
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
