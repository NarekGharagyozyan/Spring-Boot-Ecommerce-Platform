package com.smartCode.ecommerce.model.dto.action;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ActionRequestDto {

    String actionType;

    String entityType;

    LocalDateTime actionDate;

    Integer userId;

}
