package com.smartCode.ecommerce.service.payment.impl.card;

import com.smartCode.ecommerce.service.payment.strategy.PaymentStrategy;
import com.smartCode.ecommerce.util.constants.PaymentType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CardPayment implements PaymentStrategy {

    private PaymentType paymentType = PaymentType.CARD;

    @Override
    public String pay(BigDecimal amount) {
        return new StringBuilder()
                .append("Payment type is:")
                .append(paymentType.toString())
                .append(" and total amount is ")
                .append(amount.toString()).toString();
    }

    @Override
    public PaymentType getPaymentType() {
        return paymentType;
    }
}
