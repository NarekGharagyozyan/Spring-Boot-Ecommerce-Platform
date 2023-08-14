package com.smartCode.ecommerce.util.event.listener.product;

import com.smartCode.ecommerce.service.action.ActionService;
import com.smartCode.ecommerce.util.event.events.product.ProductCreateEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class ProductCreationEventListener {

    private final ActionService actionService;

    @Async
    @TransactionalEventListener
    public void handleProductCreationEvent(ProductCreateEvent productCreateEvent) {
        actionService.createAction(
                productCreateEvent.getActionType(),
                productCreateEvent.getEntityType(),
                productCreateEvent.getActionDate(),
                productCreateEvent.getUserId()
        );
    }
}
