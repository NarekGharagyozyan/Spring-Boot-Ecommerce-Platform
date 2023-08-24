package com.smartCode.ecommerce.feign;

import com.smartCode.ecommerce.model.dto.action.ActionRequestDto;
import com.smartCode.ecommerce.util.constants.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "actionService", url = "http://localhost:8082/action")
public interface ActionFeignClient {

    @PostMapping(Path.SAVE)
    ResponseEntity<Void> saveAction(@RequestBody ActionRequestDto actionRequestDto);

}
