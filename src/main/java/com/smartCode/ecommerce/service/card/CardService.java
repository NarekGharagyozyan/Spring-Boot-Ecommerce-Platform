package com.smartCode.ecommerce.service.card;

import com.smartCode.ecommerce.model.dto.card.CardRequestDto;
import com.smartCode.ecommerce.model.dto.card.CardResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CardService {

    CardResponseDto create(CardRequestDto cardRequestDto);

    void deleteAllByOwnerId(Integer ownerId);

    void deleteByCardId(Integer cardId);

    List<CardResponseDto> findByOwnerId(Integer userId);
}
