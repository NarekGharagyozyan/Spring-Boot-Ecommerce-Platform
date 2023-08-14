package com.smartCode.ecommerce.model.dto.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
public class ProductResponseDto extends ProductBaseDto {

    private Integer id;
    private LocalDate productionDate;
    private Boolean isInDeadline;

}
