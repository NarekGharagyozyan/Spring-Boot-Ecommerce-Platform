package com.smartCode.ecommerce.model.entity.product;

import com.smartCode.ecommerce.model.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false)
    private LocalDate productionDate;

    private Boolean isInDeadline;

}
