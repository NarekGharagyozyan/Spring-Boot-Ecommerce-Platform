package com.smartCode.ecommerce.feign;

import com.smartCode.ecommerce.configuration.NotificationFeignConfig;
import com.smartCode.ecommerce.model.dto.NotificationDto;
import com.smartCode.ecommerce.model.dto.card.CardRequestDto;
import com.smartCode.ecommerce.model.dto.card.CardResponseDto;
import com.smartCode.ecommerce.util.constants.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@FeignClient(value = "notificationFeign", url = "http://localhost:8082/notify",configuration = NotificationFeignConfig.class)
public interface NotificationFeignClient {

    @PostMapping()
    ResponseEntity<NotificationDto> create(@RequestBody @Valid NotificationDto notificationDto);

    @GetMapping("/ready")
    ResponseEntity<List<NotificationDto>> getReady(@RequestParam Integer userId);

    @GetMapping("/waiting")
    ResponseEntity<List<NotificationDto>> getWaiting(@RequestParam Integer userId);
}
