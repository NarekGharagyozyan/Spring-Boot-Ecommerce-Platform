package com.smartCode.ecommerce.service.card;

import com.smartCode.ecommerce.model.dto.card.CardRequestDto;
import com.smartCode.ecommerce.model.dto.card.CardResponseDto;

import java.util.List;

public interface CardService {

    CardResponseDto create(CardRequestDto cardRequestDto);

    List<CardResponseDto> findByUserId (Integer userId);

    void deleteAllByOwnerId(Integer ownerId);

    void deleteByCardId(Integer cardId);
}
