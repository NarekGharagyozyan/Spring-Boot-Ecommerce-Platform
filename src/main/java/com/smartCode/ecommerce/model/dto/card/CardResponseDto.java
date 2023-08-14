package com.smartCode.ecommerce.model.dto.card;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
public class CardResponseDto{

    private Integer id;
    private String cardName;
    private String ownerName;
    private String number;
    private String expDate;
    private Integer ownerId;
}