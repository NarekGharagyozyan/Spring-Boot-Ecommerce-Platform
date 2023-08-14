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
public class UserRequestDto extends UserBaseDto{

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotNull
    private LocalDate date;

    private String middleName;

    @NotNull
    private Gender gender;

    @NotBlank
    private String password;

}
