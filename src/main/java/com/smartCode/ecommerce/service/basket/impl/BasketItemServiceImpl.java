package com.smartCode.ecommerce.service.basket.impl;

import com.smartCode.ecommerce.exceptions.ResourceNotFoundException;
import com.smartCode.ecommerce.mapper.BasketItemMapper;
import com.smartCode.ecommerce.model.dto.basket.BasketItemResponseDto;
import com.smartCode.ecommerce.model.entity.basket.BasketItemEntity;
import com.smartCode.ecommerce.model.entity.product.ProductEntity;
import com.smartCode.ecommerce.model.entity.user.UserEntity;
import com.smartCode.ecommerce.repository.basketItem.BasketItemRepository;
import com.smartCode.ecommerce.repository.product.ProductRepository;
import com.smartCode.ecommerce.repository.user.UserRepository;
import com.smartCode.ecommerce.service.action.ActionService;
import com.smartCode.ecommerce.service.basket.BasketItemService;
import com.smartCode.ecommerce.util.constants.Action;
import com.smartCode.ecommerce.util.constants.Entity;
import com.smartCode.ecommerce.util.constants.Message;
import com.smartCode.ecommerce.util.currentUser.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketItemServiceImpl implements BasketItemService {

    private final BasketItemRepository basketItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ActionService actionService;
    private final BasketItemMapper basketItemMapper;

    @Override
    @Transactional
    public List<BasketItemResponseDto> getBasket() {
        List<BasketItemEntity> basketItems = basketItemRepository.findAllByUserId(CurrentUser.getId());
        return basketItemMapper.toResponseDtoList(basketItems);
    }

    @Override
    @Transactional
    public void deleteFromBasket(Integer basketItemId) {
        BasketItemEntity basketItem = basketItemRepository.findById(basketItemId).get();
        if (basketItem.getUser().getId().equals(CurrentUser.getId())){
            basketItemRepository.deleteById(basketItemId);
        }
    }

    @Override
    @Transactional
    public void addOrUpdate(Integer productId) {
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(Message.PRODUCT_NOT_FOUND));
        BasketItemEntity basketItem = basketItemRepository.findByUserIdAndProductId(CurrentUser.getId(), productId);
        if (basketItem == null) {
            BasketItemEntity basketItemEntity = new BasketItemEntity();
            basketItemEntity.setCount(1);
            basketItemEntity.setUser(userRepository.findById(CurrentUser.getId()).get());
            basketItemEntity.setProduct(productEntity);
            actionService.createAction(Action.ADD_TO_BASKET, Entity.PRODUCT, CurrentUser.getId());
            basketItemRepository.save(basketItemEntity);
        }
        else {
            basketItem.setCount(basketItem.getCount() + 1);
            actionService.createAction(Action.UPDATE_IN_BASKET, Entity.PRODUCT, CurrentUser.getId());
            basketItemRepository.save(basketItem);
        }
    }
}
