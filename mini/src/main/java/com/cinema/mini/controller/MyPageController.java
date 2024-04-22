package com.cinema.mini.controller;


import com.cinema.mini.domain.Member;
import com.cinema.mini.dto.MembershipInfoDto;
import com.cinema.mini.dto.ReserveDetailDto;
import com.cinema.mini.interceptor.SessionConst;
import com.cinema.mini.repository.MemberRepository;
import com.cinema.mini.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {
    private final MyPageService myPageService;
    private final MemberRepository memberRepository;
    @GetMapping("/payment")
    public String myPayment(@SessionAttribute(name = SessionConst.SESSION_NAME) Member loginMember, Model model){
        List<ReserveDetailDto> reserveDetailDtos = myPageService.reservationDetail(loginMember);
        model.addAttribute(reserveDetailDtos);
        return "view/mypage/paymentDetail";
    }

    @GetMapping("/membership")
    public String myMembership(@SessionAttribute(name= SessionConst.SESSION_NAME) Member loginMember, Model model){

        MembershipInfoDto membershipInfoDto = new MembershipInfoDto(loginMember);
        model.addAttribute("membershipInfoDto",membershipInfoDto);
        return "view/mypage/membership";
    }
    @GetMapping("/membershipinfo")
    public ResponseEntity<MembershipInfoDto> myMembershipInfo(@SessionAttribute(name= SessionConst.SESSION_NAME) Member loginMember){

        Member findMember = memberRepository.findById(loginMember.getMemberId()).orElseThrow();
        MembershipInfoDto membershipInfoDto = new MembershipInfoDto(findMember);

        log.info("membershipInfoDto.getCount={}", membershipInfoDto.getTotalReserveCount());
        return ResponseEntity.ok().body(membershipInfoDto);
    }

    @GetMapping("/memberinfo")
    public String myMemberInfo(@SessionAttribute(name=SessionConst.SESSION_NAME)Member loginMember, Model model){
        model.addAttribute("loginMember",loginMember);

        return "view/mypage/memberInfo";
    }
}
