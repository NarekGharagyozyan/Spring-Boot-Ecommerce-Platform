package com.smartCode.ecommerce.repository.order;

import com.smartCode.ecommerce.model.entity.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByUserId(Integer userId);
}