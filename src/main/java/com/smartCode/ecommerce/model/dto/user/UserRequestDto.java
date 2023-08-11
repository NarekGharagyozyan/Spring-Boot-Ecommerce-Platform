package com.smartCode.ecommerce.model.dto.user;

import com.smartCode.ecommerce.util.constants.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDto extends UserBaseDto{

    @NotBlank
    String name;

    @NotBlank
    String lastName;

    @NotNull
    LocalDate date;

    String middleName;

    @NotNull
    Gender gender;

    @NotBlank
    String password;

}
