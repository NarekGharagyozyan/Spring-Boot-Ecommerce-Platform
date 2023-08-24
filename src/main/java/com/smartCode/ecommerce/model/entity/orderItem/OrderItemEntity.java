package com.smartCode.ecommerce.model.entity.orderItem;

import com.smartCode.ecommerce.model.entity.BaseEntity;
import com.smartCode.ecommerce.model.entity.order.OrderEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_item")
public class OrderItemEntity extends BaseEntity {

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    private Integer productId;

    /*@Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private ProductDetails productDetails;*/

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrderEntity order;
}
