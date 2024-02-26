package com.cinema.mini.repository;

import com.cinema.mini.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("findById 테스트")
    void findByIdTest(){
        Optional<Member> member = memberRepository.findById((long) 2);
        assertThat(member.get().getMemberId()).isEqualTo(2);
    }
}
