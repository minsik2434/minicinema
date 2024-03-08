package com.cinema.mini.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;

    public LoginDto(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
