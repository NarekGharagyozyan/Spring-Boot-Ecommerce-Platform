package com.smartCode.ecommerce.model.dto.user;

import com.smartCode.ecommerce.util.constants.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserResponseDto extends UserBaseDto{

    private String name;

    private String middleName;

    private String lastName;

    private LocalDate date;

    private Integer age;

    private Gender gender;
}