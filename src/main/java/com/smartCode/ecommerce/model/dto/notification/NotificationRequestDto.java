package com.smartCode.ecommerce.model.dto.notification;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@Validated
public class NotificationRequestDto {

    
    private Integer userId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String description;

    @NotNull
    private Long notificationDate;

    private Long createDate;

    private Boolean sent = false;

    private String email;

}
