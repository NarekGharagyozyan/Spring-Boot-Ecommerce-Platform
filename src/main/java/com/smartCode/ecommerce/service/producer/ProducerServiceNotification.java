package com.smartCode.ecommerce.service.producer;

import com.smartCode.ecommerce.model.dto.action.ActionRequestDto;
import com.smartCode.ecommerce.model.dto.notification.NotificationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProducerServiceNotification {

    private final KafkaTemplate<String, NotificationRequestDto> kafkaTemplate;

    @Value("${kafka.notification.topic.name}")
    private String topicName;

    @Transactional
    public void sendMessage(NotificationRequestDto notification) {
        kafkaTemplate.send(topicName, notification);
    }

}

