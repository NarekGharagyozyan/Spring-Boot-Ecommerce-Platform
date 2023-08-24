package com.smartCode.ecommerce.controller;

import com.smartCode.ecommerce.model.dto.basket.BasketItemResponseDto;
import com.smartCode.ecommerce.service.basket.BasketItemService;
import com.smartCode.ecommerce.util.constants.Path;
import com.smartCode.ecommerce.util.constants.RoleConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(Path.BASKET)
public class BasketController {

    private final BasketItemService basketItemService;

    @PostMapping("/{productId}")
    @PreAuthorize("hasRole('" + RoleConstants.USER_ROLE + "')")
    public ResponseEntity<Void> addToBasket(@PathVariable @Positive Integer productId) {
        basketItemService.addOrUpdate(productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('" + RoleConstants.USER_ROLE + "')")
    public ResponseEntity<List<BasketItemResponseDto>> getBasket() {
        List<BasketItemResponseDto> basket = basketItemService.getBasket();
        return ResponseEntity.ok(basket);
    }

    @DeleteMapping("/{basketItemId}")
    @PreAuthorize("hasRole('" + RoleConstants.USER_ROLE + "')")
    public ResponseEntity<Void> deleteFromBasket(@PathVariable @Positive Integer basketItemId) {
        basketItemService.deleteFromBasket(basketItemId);
        return ResponseEntity.ok().build();
    }

}
