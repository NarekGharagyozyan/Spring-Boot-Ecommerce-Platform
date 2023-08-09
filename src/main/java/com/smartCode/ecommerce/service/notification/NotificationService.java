package com.smartCode.ecommerce.service.notification;


import com.smartCode.ecommerce.model.dto.notification.NotificationRequestDto;
import com.smartCode.ecommerce.model.dto.notification.NotificationResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NotificationService {
    NotificationResponseDto create(NotificationRequestDto notificationRequestDto);

    NotificationResponseDto createForRegistration(String generatedCode, Integer id);

    List<NotificationResponseDto> getWaiting();
    List<NotificationResponseDto> getReady();
}
