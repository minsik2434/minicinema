package com.cinema.mini.service;

import com.cinema.mini.domain.Member;
import com.cinema.mini.dto.LoginDto;
import com.cinema.mini.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    final MemberRepository memberRepository;
    @Override
    public Member memberLogin(LoginDto loginDto) {
        return memberRepository.findByLoginId(loginDto.getLoginId())
                .filter(m -> m.getPassword().equals(loginDto.getPassword())).orElse(null);

    }
}
