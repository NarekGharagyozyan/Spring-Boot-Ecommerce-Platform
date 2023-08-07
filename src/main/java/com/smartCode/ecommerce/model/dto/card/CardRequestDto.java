package com.smartCode.ecommerce.model.dto.card;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRequestDto {
        String cardName;
        String ownerName;
        String number;
        String expDate;
        Integer ownerId;
}
