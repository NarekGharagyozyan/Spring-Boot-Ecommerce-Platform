package com.smartCode.ecommerce.service.notofication;


import com.smartCode.ecommerce.model.dto.NotificationDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NotificationService {

    NotificationDto create(NotificationDto notificationEntity);

    List<NotificationDto> getReady();

    List<NotificationDto> getWaiting();
}
