package com.smartCode.ecommerce.model.dto.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductBaseDto {

    String name;
    String company;
    Double price;
    String category;
    int count;

}
