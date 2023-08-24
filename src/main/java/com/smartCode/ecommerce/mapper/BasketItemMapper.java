package com.smartCode.ecommerce.mapper;

import com.smartCode.ecommerce.model.dto.basket.BasketItemResponseDto;
import com.smartCode.ecommerce.model.dto.order.OrderResponseDto;
import com.smartCode.ecommerce.model.entity.basket.BasketItemEntity;
import com.smartCode.ecommerce.model.entity.order.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface BasketItemMapper {

    List<BasketItemResponseDto> toResponseDtoList(List<BasketItemEntity> basketItems);

}
