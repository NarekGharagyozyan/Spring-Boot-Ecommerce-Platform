package com.smartCode.ecommerce.model.dto.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto extends ProductBaseDto{

    LocalDate productionDate;

}
