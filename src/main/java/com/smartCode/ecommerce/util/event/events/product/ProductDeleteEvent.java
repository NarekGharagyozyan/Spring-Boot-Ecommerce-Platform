package com.smartCode.ecommerce.util.event.events.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class ProductDeleteEvent extends ApplicationEvent {

    private final String actionType;
    private final String entityType;
    private final LocalDateTime actionDate;
    private final Integer userId;

    public ProductDeleteEvent(Object source, String actionType, String entityType, LocalDateTime actionDate, Integer userId) {
        super(source);
        this.actionType = actionType;
        this.entityType = entityType;
        this.actionDate = actionDate;
        this.userId = userId;
    }
}
