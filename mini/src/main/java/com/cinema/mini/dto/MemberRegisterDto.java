package com.cinema.mini.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRegisterDto {
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
    @NotBlank
    private String rePassword;
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

}
