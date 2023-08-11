package com.smartCode.ecommerce.util.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RegistrationEvent extends ApplicationEvent {

    private final String code;
    private final Integer userId;
    private final String email;

    public RegistrationEvent(Object source, String code, Integer userId, String email) {
        super(source);
        this.code = code;
        this.userId = userId;
        this.email = email;
    }

}
