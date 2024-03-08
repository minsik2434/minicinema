package com.cinema.mini.repository;

import com.cinema.mini.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("findById 테스트")
    void findByIdTest(){
        Member newMember = createTestMember();
        Member saveMember = memberRepository.save(newMember);
        Member member = memberRepository.findById((newMember.getMemberId())).get();
        assertThat(saveMember).isEqualTo(member);
    }

    @Test
    @DisplayName("findByLoginId 테스트")
    void findByLoginIdTest(){
        Member newMember = createTestMember();
        Member saveMember = memberRepository.save(newMember);
        Member member = memberRepository.findByLoginId(newMember.getLoginId()).get();
        assertThat(saveMember).isEqualTo(member);
    }

    private Member createTestMember() {
        LocalDate localDate = LocalDate.parse("1999-12-25");
        return Member.builder()
                .loginId("test")
                .password("test!")
                .email("test@naver.com")
                .name("테스터")
                .birth(localDate)
                .grade("Bronze").build();
    }
}
