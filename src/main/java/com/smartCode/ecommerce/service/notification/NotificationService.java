package com.smartCode.ecommerce.service.notification;


import com.smartCode.ecommerce.model.dto.notification.NotificationRequestDto;
import com.smartCode.ecommerce.model.dto.notification.NotificationResponseDto;

import java.util.List;

public interface NotificationService {
    NotificationResponseDto create(NotificationRequestDto notificationRequestDto);
    List<NotificationResponseDto> getWaiting();
    List<NotificationResponseDto> getReady();
}
