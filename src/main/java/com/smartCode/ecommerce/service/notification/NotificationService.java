package com.smartCode.ecommerce.service.notification;


import com.smartCode.ecommerce.model.dto.notification.NotificationRequestDto;
import com.smartCode.ecommerce.model.dto.notification.NotificationResponseDto;
import com.smartCode.ecommerce.model.entity.user.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NotificationService {
    NotificationResponseDto create(NotificationRequestDto notificationRequestDto);

    void createForRegistration(String code, Integer userId, String email);

    List<NotificationResponseDto> getWaiting();
    List<NotificationResponseDto> getReady();
}
