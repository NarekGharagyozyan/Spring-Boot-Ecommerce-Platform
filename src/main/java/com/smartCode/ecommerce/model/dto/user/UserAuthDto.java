package com.smartCode.ecommerce.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserAuthDto {

    private Integer userId;

    private String accessToken;
}
