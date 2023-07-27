package com.smartCode.ecommerce.model.dto.user;

import com.smartCode.ecommerce.util.constants.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class UserRequestDto extends UserBaseDto{

    private String name;

    private String lastName;

    private LocalDate date;

    private String middleName;

    private Gender gender;

    private String password;

}
