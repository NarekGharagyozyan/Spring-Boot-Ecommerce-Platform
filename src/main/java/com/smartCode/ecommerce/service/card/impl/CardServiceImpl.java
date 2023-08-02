package com.smartCode.ecommerce.service.card.impl;

import com.smartCode.ecommerce.exceptions.DuplicationException;
import com.smartCode.ecommerce.exceptions.ValidationException;
import com.smartCode.ecommerce.model.dto.card.CardRequestDto;
import com.smartCode.ecommerce.model.dto.card.CardResponseDto;
import com.smartCode.ecommerce.service.card.CardService;
import com.smartCode.ecommerce.util.constants.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    @Async
    @Override
    @Transactional
    public void deleteByCardId(Integer cardId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(String.format("http://localhost:8081/cards/delete/%d",cardId));
    }
    @Async
    @Override
    @Transactional
    public void deleteAllByOwnerId(Integer userId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(String.format("http://localhost:8081/cards/delete/owner/%d",userId));
    }

    @Async
    @Override
    @Transactional
    public CardResponseDto create(CardRequestDto cardRequestDto) {
        int length = cardRequestDto.getNumber().toCharArray().length;
        if (length != 16) {
            throw new ValidationException(Message.CARD_NOT_FOUND);
        }
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(
                "http://localhost:8081/cards/create",
                cardRequestDto,
                CardResponseDto.class).getBody();
    }

    @Override
    @Transactional
    public List<CardResponseDto> findByUserId(Integer userId) {
        RestTemplate restTemplate = new RestTemplate();
        return List.of(Objects.requireNonNull(restTemplate.getForEntity(
                String.format("http://localhost:8081/cards/find/%d", userId),
                CardResponseDto[].class).getBody()));
    }
}
