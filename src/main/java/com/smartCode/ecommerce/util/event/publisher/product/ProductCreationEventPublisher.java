package com.smartCode.ecommerce.util.event.publisher.product;

import com.smartCode.ecommerce.util.currentUser.CurrentUser;
import com.smartCode.ecommerce.util.event.events.card.CardCreateEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ProductCreationEventPublisher {

    private  final ApplicationEventPublisher applicationEventPublisher;

    public void publishProductCreationEvent() {
        CardCreateEvent productCreateEvent = new CardCreateEvent(
                this,
                "Create",
                "Product",
                LocalDateTime.now(),
                CurrentUser.getId());

        applicationEventPublisher.publishEvent(productCreateEvent);
    }
}
