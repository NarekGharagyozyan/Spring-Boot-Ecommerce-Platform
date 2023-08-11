package com.smartCode.ecommerce.util.event.events.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductCreateEvent extends ApplicationEvent {

    private String actionType;
    private String entityType;
    private LocalDateTime actionDate;
    private Integer userId;

    public ProductCreateEvent(Object source, String actionType, String entityType, LocalDateTime actionDate, Integer userId) {
        super(source);
        this.actionType = actionType;
        this.entityType = entityType;
        this.actionDate = actionDate;
        this.userId = userId;
    }
}
