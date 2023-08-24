package com.smartCode.ecommerce.mapper;

import com.smartCode.ecommerce.model.dto.order.OrderResponseDto;
import com.smartCode.ecommerce.model.dto.user.UserRequestDto;
import com.smartCode.ecommerce.model.dto.user.UserResponseDto;
import com.smartCode.ecommerce.model.entity.order.OrderEntity;
import com.smartCode.ecommerce.model.entity.user.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponseDto toResponseDto(OrderEntity orderEntity);

    List<OrderResponseDto> toResponseDtoList(List<OrderEntity> orders);
}
