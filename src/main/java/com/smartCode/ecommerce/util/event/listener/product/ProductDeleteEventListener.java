package com.smartCode.ecommerce.util.event.listener.product;

import com.smartCode.ecommerce.service.action.ActionService;
import com.smartCode.ecommerce.util.event.events.card.CardDeleteEvent;
import com.smartCode.ecommerce.util.event.events.product.ProductDeleteEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class ProductDeleteEventListener {

    private final ActionService actionService;

    @Async
    @TransactionalEventListener
    public void handleProductDeleteEvent(ProductDeleteEvent productDeleteEvent) {
        actionService.createAction(
                productDeleteEvent.getActionType(),
                productDeleteEvent.getEntityType(),
                productDeleteEvent.getActionDate(),
                productDeleteEvent.getUserId()
        );
    }
}
