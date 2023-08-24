package com.smartCode.ecommerce.model.dto.basket;

import com.smartCode.ecommerce.model.dto.product.ProductResponseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BasketItemResponseDto {
    private Integer id;
    private Integer count;
    private ProductResponseDto product;
}
