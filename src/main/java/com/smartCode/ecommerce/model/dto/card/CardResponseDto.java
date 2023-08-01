package com.smartCode.ecommerce.model.dto.card;


import lombok.Getter;
import lombok.Setter;

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