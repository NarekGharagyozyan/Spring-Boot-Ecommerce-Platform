package com.smartCode.ecommerce.model.dto.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class ProductBaseDto {

    private String name;
    private String company;
    private Double price;
    private String category;
    private int count;

}
