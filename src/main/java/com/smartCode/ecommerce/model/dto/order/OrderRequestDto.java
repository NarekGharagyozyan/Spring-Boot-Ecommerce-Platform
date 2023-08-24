package com.smartCode.ecommerce.model.dto.order;

import com.smartCode.ecommerce.util.constants.PaymentType;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
public class OrderRequestDto {

    private PaymentType paymentType;
    private String note;

}
