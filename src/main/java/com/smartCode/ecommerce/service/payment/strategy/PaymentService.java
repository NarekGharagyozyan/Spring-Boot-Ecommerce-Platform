package com.smartCode.ecommerce.service.payment.strategy;

import com.smartCode.ecommerce.model.entity.order.OrderEntity;
import com.smartCode.ecommerce.util.constants.PaymentType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    private final Map<PaymentType, PaymentStrategy> payments = new EnumMap<>(PaymentType.class);

    public void register(PaymentType paymentType, PaymentStrategy paymentStrategy) {
        payments.put(paymentType,paymentStrategy);
    }

    public String pay(PaymentType paymentType, BigDecimal amount) {
        return payments.get(paymentType).pay(amount);
    }
}
