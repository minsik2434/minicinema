package com.cinema.mini.util;

import com.cinema.mini.domain.Member;
import com.cinema.mini.domain.MemberGrade;
import com.cinema.mini.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class MemberUtil {
    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public Member saveMember(String loginId, String password, String email, String name, String strBirth, MemberGrade grade){
        LocalDate birth = LocalDate.parse(strBirth);
        Member member = buildTestMember(loginId, password, email, name, birth, grade);
        return memberRepository.save(member);
    }

    private Member buildTestMember(String loginId, String password, String email, String name, LocalDate birth, MemberGrade grade){
        return Member.builder()
                .loginId(loginId)
                .password(password)
                .email(email)
                .name(name)
                .birth(birth)
                .grade(grade).build();
    }
}
