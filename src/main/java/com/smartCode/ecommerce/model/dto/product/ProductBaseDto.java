package com.smartCode.ecommerce.model.dto.product;

import lombok.Getter;
import lombok.Setter;

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
