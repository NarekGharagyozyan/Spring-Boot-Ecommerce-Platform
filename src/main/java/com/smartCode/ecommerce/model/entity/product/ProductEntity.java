package com.smartCode.ecommerce.model.entity.product;

import com.smartCode.ecommerce.model.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String company;

    @Column(nullable = false)
    Double price;

    @Column(nullable = false)
    String category;

    @Column(nullable = false)
    Integer count;

    @Column(nullable = false)
    LocalDate productionDate;

    Boolean isInDeadline;

}
