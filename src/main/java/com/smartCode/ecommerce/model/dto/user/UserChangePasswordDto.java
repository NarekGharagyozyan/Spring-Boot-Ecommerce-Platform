package com.smartCode.ecommerce.model.dto.user;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@Validated
public class UserChangePasswordDto {
    @NotBlank
    private String password;
    @NotBlank
    private String changePassword;
    @NotBlank
    private String repeatPassword;
}
