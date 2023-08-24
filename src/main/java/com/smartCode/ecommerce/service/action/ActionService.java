package com.smartCode.ecommerce.service.action;

import java.time.LocalDateTime;

public interface ActionService {
    void createAction(String actionType, String entityType, Integer userId);
}
