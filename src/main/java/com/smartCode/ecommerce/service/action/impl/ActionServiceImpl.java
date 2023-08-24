package com.smartCode.ecommerce.service.action.impl;

import com.smartCode.ecommerce.feign.ActionFeignClient;
import com.smartCode.ecommerce.model.dto.action.ActionRequestDto;
import com.smartCode.ecommerce.service.action.ActionService;
import com.smartCode.ecommerce.service.producer.ProducerServiceActivity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final ProducerServiceActivity producerServiceActivity;
    @Override
    @Transactional
    public void createAction(String actionType, String entityType, LocalDateTime actionDate, Integer userId) {
        ActionRequestDto actionRequestDto = new ActionRequestDto();
        actionRequestDto.setActionType(actionType);
        actionRequestDto.setEntityType(entityType);
        actionRequestDto.setActionDate(actionDate);
        actionRequestDto.setUserId(userId);

        producerServiceActivity.sendMessage(actionRequestDto);
    }
}
