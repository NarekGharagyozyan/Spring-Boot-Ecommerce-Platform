package com.smartCode.ecommerce.model.dto.card;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
public class CardRequestDto {
        private String cardName;
        private String ownerName;
        private String number;
        private String expDate;
        private Integer ownerId;
}
