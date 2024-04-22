package com.cinema.mini.dto;

import com.cinema.mini.domain.Member;
import com.cinema.mini.domain.MemberGrade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembershipInfoDto {
    private String name;
    private MemberGrade memberGrade;
    private int totalReserveCount;

    public MembershipInfoDto(Member member){
        this.name = member.getName();
        this.memberGrade = member.getGrade();
        this.totalReserveCount = member.getTotalReserveCount();
    }
}
