package com.smartCode.ecommerce.model.dto.card;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CardRequestDto {
        private String cardName;
        private String ownerName;
        private String number;
        private String expDate;
        private Integer ownerId;
}
