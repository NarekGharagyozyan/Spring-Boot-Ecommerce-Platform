package com.smartCode.ecommerce.repository.orderItem;

import com.smartCode.ecommerce.model.entity.orderItem.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Integer> {
}