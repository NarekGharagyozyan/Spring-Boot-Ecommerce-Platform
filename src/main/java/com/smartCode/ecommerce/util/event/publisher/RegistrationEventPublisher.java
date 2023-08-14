package com.smartCode.ecommerce.util.event.publisher;

import com.smartCode.ecommerce.model.entity.user.UserEntity;
import com.smartCode.ecommerce.util.event.RegistrationEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishRegistrationEvent(UserEntity userEntity) {
        RegistrationEvent registrationEvent = new RegistrationEvent(
                                            this,
                                            userEntity.getCode(),
                                            userEntity.getId(),
                                            userEntity.getEmail());

        applicationEventPublisher.publishEvent(registrationEvent);
    }

}
