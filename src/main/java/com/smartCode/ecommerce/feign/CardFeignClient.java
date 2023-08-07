package com.smartCode.ecommerce.feign;

import com.smartCode.ecommerce.model.dto.card.CardRequestDto;
import com.smartCode.ecommerce.model.dto.card.CardResponseDto;
import com.smartCode.ecommerce.util.constants.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@FeignClient(value = "cardService", url = "http://localhost:8081/cards")
public interface CardFeignClient {

    @PostMapping("/create")
    ResponseEntity<CardResponseDto> createCard(@RequestBody @Valid CardRequestDto cardRequestDto);

    @GetMapping(Path.FIND_WITH_OWNER_ID)
    ResponseEntity<List<CardResponseDto>> findByOwnerId(@PathVariable @Positive Integer ownerId);
}
