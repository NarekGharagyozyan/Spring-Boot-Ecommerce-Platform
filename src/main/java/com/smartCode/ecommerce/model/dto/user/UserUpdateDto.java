package com.smartCode.ecommerce.model.dto.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
public class UserUpdateDto extends UserBaseDto{

    private String password;

}
