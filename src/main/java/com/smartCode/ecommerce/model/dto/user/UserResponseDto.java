package com.smartCode.ecommerce.model.dto.user;

import com.smartCode.ecommerce.model.dto.card.CardResponseDto;
import com.smartCode.ecommerce.util.constants.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDto extends UserBaseDto{

    Integer id;

    String name;

    String middleName;

    String lastName;

    LocalDate date;

    Integer age;

    Gender gender;

    List<CardResponseDto> cards;
}
