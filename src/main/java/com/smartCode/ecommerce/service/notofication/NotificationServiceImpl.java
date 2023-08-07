package com.smartCode.ecommerce.service.notofication;

import com.smartCode.ecommerce.feign.NotificationFeignClient;
import com.smartCode.ecommerce.model.dto.NotificationDto;
import com.smartCode.ecommerce.util.currentUser.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationFeignClient notificationFeignClient;

    @Override
    @Transactional
    public NotificationDto create(NotificationDto notificationEntity) {
        return notificationFeignClient.create(notificationEntity).getBody();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationDto> getReady() {
        return notificationFeignClient.getReady(CurrentUser.getId()).getBody();
    }


    @Override
    @Transactional(readOnly = true)
    public List<NotificationDto> getWaiting() {
        return notificationFeignClient.getWaiting(CurrentUser.getId()).getBody();
    }
}
