package com.smartCode.ecommerce.util.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEvent;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationEvent extends ApplicationEvent {
    String code;
    Integer userId;
    String email;

    public RegistrationEvent(Object source, String code, Integer userId, String email) {
        super(source);
        this.code = code;
        this.userId = userId;
        this.email = email;
    }

}
