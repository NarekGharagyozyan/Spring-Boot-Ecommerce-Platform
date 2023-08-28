package com.smartCode.ecommerce.service.order.impl;

import com.smartCode.ecommerce.exceptions.ResourceNotFoundException;
import com.smartCode.ecommerce.mapper.OrderMapper;
import com.smartCode.ecommerce.mapper.ProductMapper;
import com.smartCode.ecommerce.model.dto.order.OrderResponseDto;
import com.smartCode.ecommerce.model.entity.basket.BasketItemEntity;
import com.smartCode.ecommerce.model.entity.order.OrderEntity;
import com.smartCode.ecommerce.model.entity.orderItem.OrderItemEntity;
import com.smartCode.ecommerce.model.entity.product.ProductEntity;
import com.smartCode.ecommerce.repository.basketItem.BasketItemRepository;
import com.smartCode.ecommerce.repository.order.OrderRepository;
import com.smartCode.ecommerce.repository.user.UserRepository;
import com.smartCode.ecommerce.service.action.ActionService;
import com.smartCode.ecommerce.service.order.OrderService;
import com.smartCode.ecommerce.service.payment.strategy.PaymentService;
import com.smartCode.ecommerce.util.constants.Action;
import com.smartCode.ecommerce.util.constants.Entity;
import com.smartCode.ecommerce.util.constants.Message;
import com.smartCode.ecommerce.util.constants.OrderStatus;
import com.smartCode.ecommerce.util.constants.PaymentType;
import com.smartCode.ecommerce.util.currentUser.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final BasketItemRepository basketItemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final OrderMapper orderMapper;
    private final ActionService actionService;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public OrderResponseDto createOrder(PaymentType paymentType, String note) {
        List<BasketItemEntity> list = basketItemRepository.findAllByUserId(CurrentUser.getId());

        OrderEntity order = createOrderAndSetUserStatusNote(note);
        Map<Integer, BigDecimal> map = puttingCountAndPriceInTheMap(list);
        BigDecimal totalPrice = countingTotalPrice(map);

        order.setTotalAmount(totalPrice);
        List<OrderItemEntity> orderItems = getOrderItemEntities(list, order);
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        System.out.println(paymentService.pay(paymentType, totalPrice));

        actionService.createAction(Action.CREATE, Entity.ORDER, CurrentUser.getId());
        return orderMapper.toResponseDto(order);
    }

    @Override
    @Transactional
    public List<OrderResponseDto> getAllOrders() {
        List<OrderEntity> allOrders = orderRepository.findAllByUserId(CurrentUser.getId());
        return orderMapper.toResponseDtoList(allOrders);
    }

    @Override
    @Transactional
    public OrderResponseDto getOrderById(Integer orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException(Message.ORDER_NOT_FOUND));
        if (order.getUser().getId().equals(CurrentUser.getId())) {
            return orderMapper.toResponseDto(order);
        } else throw new ResourceNotFoundException(Message.ORDER_NOT_FOUND);
    }

    private OrderEntity createOrderAndSetUserStatusNote(String note) {
        OrderEntity order = new OrderEntity();
        order.setUser(userRepository.findById(CurrentUser.getId()).get());
        order.setStatus(OrderStatus.ACCEPTED);
        order.setNote(note);
        return order;
    }

    private static BigDecimal countingTotalPrice(Map<Integer, BigDecimal> map) {
        BigDecimal totalPrice = new BigDecimal(0);
        Set<Map.Entry<Integer, BigDecimal>> entries = map.entrySet();
        for (Map.Entry<Integer, BigDecimal> entry : entries) {
            BigDecimal multiply = entry.getValue().multiply(new BigDecimal(entry.getKey()));
            totalPrice = totalPrice.add(multiply);
        }
        return totalPrice;
    }

    private Map<Integer, BigDecimal> puttingCountAndPriceInTheMap(List<BasketItemEntity> list) {
        Map<Integer, BigDecimal> map = new HashMap<>();
        for (BasketItemEntity basketItemEntity : list) {
            map.put(basketItemEntity.getCount(), basketItemEntity.getProduct().getPrice());
        }
        return map;
    }

    private List<OrderItemEntity> getOrderItemEntities(List<BasketItemEntity> list, OrderEntity order) {
        List<OrderItemEntity> orderItems = new ArrayList<>();
        for (BasketItemEntity basketItemEntity : list) {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setOrder(order);
            orderItemEntity.setCount(basketItemEntity.getCount());
//            orderItemEntity.setProductDetails(basketItemEntity.getProduct().getId());
            ProductEntity product = basketItemEntity.getProduct();
            orderItemEntity.setProductDetails(productMapper.toProductDetails(product));
            orderItemEntity.setTotalPrice(basketItemEntity.getProduct().getPrice().multiply(new BigDecimal(basketItemEntity.getCount())));
            orderItems.add(orderItemEntity);
        }
        return orderItems;
    }
}
