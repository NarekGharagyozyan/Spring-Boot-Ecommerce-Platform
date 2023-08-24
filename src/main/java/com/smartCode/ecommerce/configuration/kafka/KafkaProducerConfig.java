package com.smartCode.ecommerce.configuration.kafka;

import com.smartCode.ecommerce.model.dto.action.ActionRequestDto;
import com.smartCode.ecommerce.model.dto.notification.NotificationRequestDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${spring.kafka.bootstrap-server}")
    private String bootstrapAddress;

    @Bean
    public ProducerFactory<String, NotificationRequestDto> notifyProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, 1024);
        configProps.put(JsonSerializer.TYPE_MAPPINGS, "notification:com.smartCode.ecommerce.model.dto.notification.NotificationRequestDto");
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public ProducerFactory<String, ActionRequestDto> actionProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, 1024);
        configProps.put(JsonSerializer.TYPE_MAPPINGS, "audit:com.smartCode.ecommerce.model.dto.action.ActionRequestDto");
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, NotificationRequestDto> notifyKafkaTemplate() {
        return new KafkaTemplate<>(notifyProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, ActionRequestDto> actionKafkaTemplate() {
        return new KafkaTemplate<>(actionProducerFactory());
    }
}