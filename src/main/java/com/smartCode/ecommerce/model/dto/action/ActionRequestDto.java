package com.smartCode.ecommerce.model.dto.action;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ActionRequestDto {

    private String actionType;

    private String entityType;

    private LocalDateTime actionDate;

    private Integer userId;

}
