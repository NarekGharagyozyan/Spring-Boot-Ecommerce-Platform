package com.smartCode.ecommerce.model.dto.user;

import com.smartCode.ecommerce.util.constants.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;

@MappedSuperclass
@Getter
@Setter
public class UserBaseDto {

    private String username;

    @Email
    private String email;

    private String phone;

}
