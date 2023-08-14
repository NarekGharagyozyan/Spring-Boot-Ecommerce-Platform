package com.smartCode.ecommerce.util.event.listener;

import com.smartCode.ecommerce.service.notification.NotificationService;
import com.smartCode.ecommerce.util.event.RegistrationEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class RegistrationEventListener {

    private final NotificationService notificationService;

    @Async
    @TransactionalEventListener
    public void handleRegistrationEvent(RegistrationEvent registrationEvent) {
        notificationService.createForRegistration(
                registrationEvent.getCode(),
                registrationEvent.getUserId(),
                registrationEvent.getEmail()
        );
    }

}
