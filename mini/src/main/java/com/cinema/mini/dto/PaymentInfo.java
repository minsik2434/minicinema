package com.cinema.mini.dto;

import com.cinema.mini.domain.Member;
import com.cinema.mini.domain.MemberGrade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentInfo {
    private String memberGrade;
    private int discountPercent;
    private int originPrice;
    private int discountPrice;
    private int totalPrice;

    public PaymentInfo(Member member, int totalPersonNum){
        this.memberGrade = member.getGrade().name();
        this.originPrice = 15000*totalPersonNum;
        if(member.getGrade() == MemberGrade.BRONZE){
            this.discountPercent = 3;
            this.discountPrice = Math.round(originPrice * 0.03f);
            this.totalPrice = originPrice - discountPrice;
        }
        else if(member.getGrade() == MemberGrade.GOLD){
            this.discountPercent = 5;
            this.discountPrice = Math.round(originPrice * 0.05f);
            this.totalPrice = originPrice - discountPrice;
        }
        else if(member.getGrade() == MemberGrade.PLATINUM){
            this.discountPercent = 7;
            this.discountPrice = Math.round(originPrice * 0.07f);
            this.totalPrice = originPrice - discountPrice;
        }
        else if(member.getGrade() == MemberGrade.DIA){
            this.discountPercent = 10;
            this.discountPrice = Math.round(originPrice * 0.1f);
            this.totalPrice = originPrice - discountPrice;
        }
    }
}
