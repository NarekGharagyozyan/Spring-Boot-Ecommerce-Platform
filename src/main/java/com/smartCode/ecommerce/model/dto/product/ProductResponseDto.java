package com.smartCode.ecommerce.model.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductResponseDto extends ProductBaseDto{

    private Integer id;

    private LocalDate productionDate;

    private Boolean isInDeadline;

}
