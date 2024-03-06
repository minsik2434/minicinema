package com.cinema.mini.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ProfileUpdateDto {
    @NotBlank
    private String loginId;
    @Email
    private String email;
    @NotBlank
    private String Name;
    @NotBlank
    private String birthYear;
    @NotBlank
    private String birthMonth;
    @NotBlank
    private String birthDay;
}
