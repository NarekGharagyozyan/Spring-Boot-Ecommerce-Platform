package com.smartCode.ecommerce.feign;

import com.smartCode.ecommerce.configuration.feign.NotificationFeignConfig;
import com.smartCode.ecommerce.model.dto.notification.NotificationRequestDto;
import com.smartCode.ecommerce.model.dto.notification.NotificationResponseDto;
import com.smartCode.ecommerce.util.constants.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "notificationFeign", url = "http://localhost:8082/notify", configuration = NotificationFeignConfig.class)
public interface NotificationFeignClient {

    @PostMapping(Path.CREATE)
    ResponseEntity<NotificationResponseDto> create(@RequestBody NotificationRequestDto notificationDto);

    @GetMapping(Path.READY)
    ResponseEntity<List<NotificationResponseDto>> getReady(@RequestParam Integer userId);

    @GetMapping(Path.WAITING)
    ResponseEntity<List<NotificationResponseDto>> getWaiting(@RequestParam Integer userId);

    @PostMapping(Path.VERIFY)
    ResponseEntity<NotificationResponseDto> sendVerificationCode(@RequestBody NotificationRequestDto notificationRequestDto);
}
