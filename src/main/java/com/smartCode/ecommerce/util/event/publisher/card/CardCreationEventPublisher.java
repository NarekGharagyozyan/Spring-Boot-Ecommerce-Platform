package com.smartCode.ecommerce.util.event.publisher.card;

import com.smartCode.ecommerce.model.dto.card.CardRequestDto;
import com.smartCode.ecommerce.util.event.events.card.CardCreateEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CardCreationEventPublisher {

    ApplicationEventPublisher applicationEventPublisher;

    public void publishCardCreationEvent(CardRequestDto cardRequestDto) {
        CardCreateEvent cardCreateEvent = new CardCreateEvent(
                this,
                "Create",
                "Card",
                LocalDateTime.now(),
                cardRequestDto.getOwnerId());

        applicationEventPublisher.publishEvent(cardCreateEvent);
    }
}
