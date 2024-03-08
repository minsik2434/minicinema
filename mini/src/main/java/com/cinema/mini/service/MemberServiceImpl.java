package com.cinema.mini.service;

import com.cinema.mini.domain.Member;
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
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    public static final String BRONZE = "Bronze";

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
                .grade(BRONZE).build();
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
        Member member = memberRepository.findById(memberId).get();
        member.setLoginId(profileUpdateDto.getLoginId());
        member.setEmail(profileUpdateDto.getEmail());
        member.setName(profileUpdateDto.getName());
        member.setBirth(getProfileBirth(profileUpdateDto));
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
