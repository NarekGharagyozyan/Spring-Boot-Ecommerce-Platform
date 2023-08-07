package com.smartCode.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDto {

    private Integer id;

    private Integer userId;

    private String title;

    private String content;

    private String description;

    private long notificationDate;

    private long creationDate;

    private boolean sent;

    private String email;

}
