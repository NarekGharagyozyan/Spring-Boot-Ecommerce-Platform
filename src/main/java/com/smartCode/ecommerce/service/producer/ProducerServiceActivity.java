package com.smartCode.ecommerce.service.producer;

import com.smartCode.ecommerce.model.dto.action.ActionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProducerServiceActivity {

    private final KafkaTemplate<String, ActionRequestDto> kafkaTemplate;

    @Value("${kafka.activity.topic.name}")
    private String topicName;

    @Transactional
    public void sendMessage(ActionRequestDto action) {
        Message<ActionRequestDto> message =
                MessageBuilder.withPayload(action)
                                .setHeader(KafkaHeaders.TOPIC, topicName)
                                        .build();

        kafkaTemplate.send(message);
    }

}
