package com.smartCode.ecommerce.model.dto.card;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardResponseDto{

    Integer id;
    String cardName;
    String ownerName;
    String number;
    String expDate;
    Integer ownerId;
}