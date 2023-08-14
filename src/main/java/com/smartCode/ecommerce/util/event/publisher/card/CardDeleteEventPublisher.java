package com.smartCode.ecommerce.util.event.publisher.card;

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
public class CardDeleteEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishCardDeleteEvent() {
        CardCreateEvent cardDeleteEvent = new CardCreateEvent(
                this,
                "Delete",
                "Card",
                LocalDateTime.now(),
                CurrentUser.getId());

        applicationEventPublisher.publishEvent(cardDeleteEvent);
    }
}
