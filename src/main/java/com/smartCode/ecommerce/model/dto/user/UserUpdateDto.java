package com.smartCode.ecommerce.model.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto extends UserBaseDto{

    private String password;

}
