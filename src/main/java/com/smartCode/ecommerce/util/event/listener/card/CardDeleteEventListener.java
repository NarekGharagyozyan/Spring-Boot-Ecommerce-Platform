package com.smartCode.ecommerce.util.event.listener.card;

import com.smartCode.ecommerce.service.action.ActionService;
import com.smartCode.ecommerce.util.event.events.card.CardDeleteEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class CardDeleteEventListener {

    private final ActionService actionService;

    @Async
    @TransactionalEventListener
    public void handleCardDeleteEvent(CardDeleteEvent cardDeleteEvent) {
        actionService.createAction(
                cardDeleteEvent.getActionType(),
                cardDeleteEvent.getEntityType(),
                cardDeleteEvent.getActionDate(),
                cardDeleteEvent.getUserId()
        );
    }
}
