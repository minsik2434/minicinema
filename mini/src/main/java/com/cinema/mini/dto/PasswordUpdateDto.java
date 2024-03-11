package com.cinema.mini.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordUpdateDto {
    @NotBlank
    private String curPassword;
    @NotBlank
    private String newPassword;
    @NotBlank
    private String reNewPassword;

    public PasswordUpdateDto(){
    }
}
