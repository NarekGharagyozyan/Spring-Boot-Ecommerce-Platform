package com.smartCode.ecommerce.service.basket;

import com.smartCode.ecommerce.model.dto.basket.BasketItemResponseDto;
import com.smartCode.ecommerce.model.entity.basket.BasketItemEntity;
import com.smartCode.ecommerce.repository.basketItem.BasketItemRepository;

import java.util.List;

public interface BasketItemService{

    void addOrUpdate(Integer productId);

    List<BasketItemResponseDto> getBasket();
    void deleteFromBasket(Integer basketItemId);
}