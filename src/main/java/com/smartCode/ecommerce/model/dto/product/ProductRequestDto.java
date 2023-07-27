package com.smartCode.ecommerce.model.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductRequestDto extends ProductBaseDto{

    private LocalDate productionDate;

}
