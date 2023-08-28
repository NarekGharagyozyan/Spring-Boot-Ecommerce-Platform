package com.smartCode.ecommerce.model.dto.product.productDetails;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class ProductDetails implements Serializable{
    private Integer id;
    private String name;
    private String company;
    private BigDecimal price;
    private String category;
    private int count;
    private LocalDate productionDate;
}
