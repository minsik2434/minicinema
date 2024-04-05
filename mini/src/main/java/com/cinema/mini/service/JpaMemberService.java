package com.cinema.mini.service;

import com.cinema.mini.domain.Member;
import com.cinema.mini.domain.MemberGrade;
import com.cinema.mini.dto.LoginDto;
import com.cinema.mini.dto.MemberRegisterDto;
import com.cinema.mini.dto.ProfileUpdateDto;
import com.cinema.mini.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;


@Service
@Slf4j
@RequiredArgsConstructor
public class JpaMemberService implements MemberService{
    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public Member memberRegister(MemberRegisterDto memberRegisterDto) {
        LocalDate memberBirth = getMemberBirth(memberRegisterDto);
        Member member = Member.builder()
                .loginId(memberRegisterDto.getLoginId())
                .password(memberRegisterDto.getPassword())
                .email(memberRegisterDto.getEmail())
                .name(memberRegisterDto.getName())
                .birth(memberBirth)
                .grade(MemberGrade.BRONZE).build();
        return memberRepository.save(member);
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

    @Override
    @Transactional
    public void profileUpdate(long memberId, ProfileUpdateDto profileUpdateDto) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.setLoginId(profileUpdateDto.getLoginId());
        member.setEmail(profileUpdateDto.getEmail());
        member.setName(profileUpdateDto.getName());
        member.setBirth(getProfileBirth(profileUpdateDto));
        memberRepository.save(member);
    }

    @Override
    @Transactional
    public void passwordUpdate(long memberId, String password) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.setPassword(password);
        memberRepository.save(member);
    }

    private LocalDate getMemberBirth(MemberRegisterDto memberRegisterDto) {
        String birthString = memberRegisterDto.getBirthYear() +"-"+ memberRegisterDto.getBirthMonth() +"-"+
                memberRegisterDto.getBirthDay();
        return LocalDate.parse(birthString);
    }
    private LocalDate getProfileBirth(ProfileUpdateDto profileUpdateDto) {
        String birthString = profileUpdateDto.getBirthYear() +"-"+ profileUpdateDto.getBirthMonth() +"-"+
                profileUpdateDto.getBirthDay();
        return LocalDate.parse(birthString);
    }

}
