package com.smartCode.ecommerce.service.action.impl;

import com.smartCode.ecommerce.feign.ActionFeignClient;
import com.smartCode.ecommerce.model.dto.action.ActionRequestDto;
import com.smartCode.ecommerce.service.action.ActionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final ActionFeignClient actionFeignClient;
    @Override
    @Transactional
    public void createAction(String actionType, String entityType, LocalDateTime actionDate, Integer userId) {
        ActionRequestDto actionRequestDto = new ActionRequestDto();
        actionRequestDto.setActionType(actionType);
        actionRequestDto.setEntityType(entityType);
        actionRequestDto.setActionDate(actionDate);
        actionRequestDto.setUserId(userId);

        actionFeignClient.saveAction(actionRequestDto);
    }
}
