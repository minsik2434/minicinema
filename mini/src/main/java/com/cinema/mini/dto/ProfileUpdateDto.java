package com.cinema.mini.dto;

import com.cinema.mini.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
public class ProfileUpdateDto {
    @NotBlank
    private String loginId;
    @Email
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String birthYear;
    @NotBlank
    private String birthMonth;
    @NotBlank
    private String birthDay;

    public ProfileUpdateDto(Member member){
        this.loginId = member.getLoginId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.birthYear = Integer.toString(member.getBirth().getYear());
        this.birthMonth = Integer.toString(member.getBirth().getMonthValue());
        this.birthDay = Integer.toString(member.getBirth().getDayOfMonth());
    }

    public ProfileUpdateDto(){
    }
}
