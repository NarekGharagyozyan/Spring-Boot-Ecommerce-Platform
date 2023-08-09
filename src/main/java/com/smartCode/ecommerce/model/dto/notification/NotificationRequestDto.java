package com.smartCode.ecommerce.model.dto.notification;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationRequestDto {

    
    Integer userId;

    @NotBlank
    String title;

    @NotBlank
    String content;

    String description;

    @NotNull
    Long notificationDate;

    Long createDate;

    Boolean sent = false;

    String email;

}
