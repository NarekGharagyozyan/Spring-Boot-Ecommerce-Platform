package com.smartCode.ecommerce.service.order;

import com.smartCode.ecommerce.model.dto.order.OrderResponseDto;
import com.smartCode.ecommerce.util.constants.PaymentType;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(PaymentType paymentType, String note);

    List<OrderResponseDto> getAllOrders();

    OrderResponseDto getOrderById(Integer orderId);
}