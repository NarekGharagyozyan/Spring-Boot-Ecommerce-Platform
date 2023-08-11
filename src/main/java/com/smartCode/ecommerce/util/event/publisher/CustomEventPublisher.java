package com.smartCode.ecommerce.util.event.publisher;

import com.smartCode.ecommerce.model.entity.user.UserEntity;
import com.smartCode.ecommerce.util.event.RegistrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishRegistrationEvent(UserEntity userEntity) {
        var registrationEvent = new RegistrationEvent(this, userEntity.getCode(), userEntity.getId(), userEntity.getEmail());
        applicationEventPublisher.publishEvent(registrationEvent);
    }

}
