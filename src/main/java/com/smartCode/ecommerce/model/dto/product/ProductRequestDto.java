package com.smartCode.ecommerce.model.dto.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class ProductRequestDto extends ProductBaseDto{

    @NotNull
    private LocalDate productionDate;

}
