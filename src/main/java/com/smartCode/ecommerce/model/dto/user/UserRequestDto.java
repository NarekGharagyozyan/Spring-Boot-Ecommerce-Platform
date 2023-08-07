package com.smartCode.ecommerce.model.dto.user;

import com.smartCode.ecommerce.util.constants.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDto extends UserBaseDto{

    String name;

    String lastName;

    LocalDate date;

    String middleName;

    Gender gender;

    String password;

}
