package com.smartCode.ecommerce.model.dto.notification;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationResponseDto {

    Integer id;

    Integer userId;

    String title;

    String content;

    String description;

    Long notificationDate;

    String email;

}
