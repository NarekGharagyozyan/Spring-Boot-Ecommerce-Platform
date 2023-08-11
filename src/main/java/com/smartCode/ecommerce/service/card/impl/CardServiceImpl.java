package com.smartCode.ecommerce.service.card.impl;

import com.smartCode.ecommerce.exceptions.ValidationException;
import com.smartCode.ecommerce.feign.CardFeignClient;
import com.smartCode.ecommerce.model.dto.card.CardRequestDto;
import com.smartCode.ecommerce.model.dto.card.CardResponseDto;
import com.smartCode.ecommerce.service.card.CardService;
import com.smartCode.ecommerce.util.constants.Message;
import com.smartCode.ecommerce.util.currentUser.CurrentUser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    CardFeignClient cardFeignClient;

    @Async
    @Override
    @Transactional
    public void deleteByCardId(Integer cardId) {
        /*RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(String.format("http://localhost:8081/cards/delete/%d",cardId));*/
        cardFeignClient.deleteByCardId(cardId);
    }

    @Async
    @Override
    @Transactional
    public void deleteAllByOwnerId(Integer userId) {
        /*RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(String.format("http://localhost:8081/cards/delete/owner/%d",userId));*/
        cardFeignClient.deleteAllByOwnerId(userId);
    }


    @Override
    @Transactional
    public CardResponseDto create(CardRequestDto cardRequestDto) {
        String number = cardRequestDto.getNumber();
        cardRequestDto.setOwnerId(CurrentUser.getId());
        int length = number.replace(" ", "").toCharArray().length;
        if (length != 16) {
            throw new ValidationException(Message.CARD_NOT_FOUND);
        }
        /*RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(
                "http://localhost:8081/cards/create",
                cardRequestDto,
                CardResponseDto.class).getBody();*/
        return cardFeignClient.createCard(cardRequestDto).getBody();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CardResponseDto> findByOwnerId(Integer userId) {
        /*RestTemplate restTemplate = new RestTemplate();
        return List.of(Objects.requireNonNull(restTemplate.getForEntity(
                String.format("http://localhost:8081/cards/find/%d", userId),
                CardResponseDto[].class).getBody()));*/

        return cardFeignClient.findByOwnerId(userId).getBody();
    }
}
