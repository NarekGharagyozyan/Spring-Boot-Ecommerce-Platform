package com.smartCode.ecommerce.service.notification.impl;

import com.smartCode.ecommerce.exceptions.ResourceNotFoundException;
import com.smartCode.ecommerce.feign.NotificationFeignClient;
import com.smartCode.ecommerce.model.dto.notification.NotificationRequestDto;
import com.smartCode.ecommerce.model.dto.notification.NotificationResponseDto;
import com.smartCode.ecommerce.model.entity.user.UserEntity;
import com.smartCode.ecommerce.repository.UserRepository;
import com.smartCode.ecommerce.service.notification.NotificationService;
import com.smartCode.ecommerce.util.constants.Message;
import com.smartCode.ecommerce.util.currentUser.CurrentUser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationFeignClient notificationFeignClient;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public NotificationResponseDto create(NotificationRequestDto notificationRequestDto) {
        UserEntity userEntity = userRepository.findById(CurrentUser.getId()).orElseThrow(() -> new ResourceNotFoundException(Message.USER_NOT_FOUND));

        notificationRequestDto.setCreateDate(System.currentTimeMillis());
        notificationRequestDto.setEmail(userEntity.getEmail());
        notificationRequestDto.setUserId(CurrentUser.getId());
        return notificationFeignClient.create(notificationRequestDto).getBody();
    }

    @Override
    @Transactional
    public void createForRegistration(String code, Integer userId, String email) {
        NotificationRequestDto notificationRequestDto = new NotificationRequestDto();
        notificationRequestDto.setTitle(Message.EMAIL_SUBJECT);
        notificationRequestDto.setContent(Message.EMAIL_MESSAGE + code);
        notificationRequestDto.setEmail(email);
        notificationRequestDto.setUserId(userId);

        notificationFeignClient.sendVerificationCode(notificationRequestDto);
    }

    @Override
    @Transactional
    public List<NotificationResponseDto> getWaiting() {
        return notificationFeignClient.getWaiting(CurrentUser.getId()).getBody();
    }

    @Override
    @Transactional
    public List<NotificationResponseDto> getReady() {
        return notificationFeignClient.getReady(CurrentUser.getId()).getBody();
    }
}
