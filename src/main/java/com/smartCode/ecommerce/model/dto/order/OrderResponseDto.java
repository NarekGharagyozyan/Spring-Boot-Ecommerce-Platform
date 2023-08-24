package com.smartCode.ecommerce.model.dto.order;

import com.smartCode.ecommerce.model.dto.orderItem.OrderItemResponseDto;
import com.smartCode.ecommerce.model.entity.orderItem.OrderItemEntity;
import com.smartCode.ecommerce.util.constants.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class OrderResponseDto {
    private Integer id;
    private OrderStatus status;
    private String note;
    private BigDecimal totalAmount;
    private List<OrderItemResponseDto> orderItems;

}
