package com.smartCode.ecommerce.model.dto.user;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@Validated
public class UserLoginDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
