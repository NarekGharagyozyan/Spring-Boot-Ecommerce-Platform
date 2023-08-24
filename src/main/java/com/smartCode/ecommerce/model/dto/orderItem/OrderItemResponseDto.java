package com.smartCode.ecommerce.model.dto.orderItem;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemResponseDto {

    private Integer id;

    private Integer count;

    private BigDecimal totalPrice;

    private Integer productId;
}
