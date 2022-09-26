package kitchenpos.takeoutorder.application;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import kitchenpos.takeoutorder.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TakeoutOrderService {
    private final TakeoutOrderRepository takeoutOrderRepository;

    public TakeoutOrderService(
        final TakeoutOrderRepository takeoutOrderRepository
    ) {
        this.takeoutOrderRepository = takeoutOrderRepository;
    }

    @Transactional
    public TakeoutOrder create(final TakeoutOrder request) {
        final TakeoutOrderType type = request.getType();
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException();
        }
        final List<TakeoutOrderLineItem> takeoutOrderLineItemRequests = request.getOrderLineItems();
        if (Objects.isNull(takeoutOrderLineItemRequests) || takeoutOrderLineItemRequests.isEmpty()) {
            throw new IllegalArgumentException();
        }

        // 주문한 메뉴가 실존하는지 검증

        final List<TakeoutOrderLineItem> takeoutOrderLineItems = new ArrayList<>();
        for (final TakeoutOrderLineItem takeoutOrderLineItemRequest : takeoutOrderLineItemRequests) {
            final long quantity = takeoutOrderLineItemRequest.getQuantity();
            if (type != TakeoutOrderType.EAT_IN) {
                if (quantity < 0) {
                    throw new IllegalArgumentException();
                }
            }

            // 메뉴가 전시 중인지 검증

            // 메뉴의 현재 가격와 주문 금액이 일치하는지

            final TakeoutOrderLineItem takeoutOrderLineItem = new TakeoutOrderLineItem();
            takeoutOrderLineItem.setQuantity(quantity);
            takeoutOrderLineItems.add(takeoutOrderLineItem);
        }
        TakeoutOrder takeoutOrder = new TakeoutOrder();
        takeoutOrder.setId(UUID.randomUUID());
        takeoutOrder.setType(type);
        takeoutOrder.setStatus(TakeoutOrderStatus.WAITING);
        takeoutOrder.setOrderDateTime(LocalDateTime.now());
        takeoutOrder.setOrderLineItems(takeoutOrderLineItems);
        if (type == TakeoutOrderType.DELIVERY) {
            final String deliveryAddress = request.getDeliveryAddress();
            if (Objects.isNull(deliveryAddress) || deliveryAddress.isEmpty()) {
                throw new IllegalArgumentException();
            }
            takeoutOrder.setDeliveryAddress(deliveryAddress);
        }
        return takeoutOrderRepository.save(takeoutOrder);
    }

    @Transactional
    public TakeoutOrder accept(final UUID orderId) {
        final TakeoutOrder takeoutOrder = takeoutOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (takeoutOrder.getStatus() != TakeoutOrderStatus.WAITING) {
            throw new IllegalStateException();
        }
        if (takeoutOrder.getType() == TakeoutOrderType.DELIVERY) {
            BigDecimal sum = BigDecimal.ZERO;

            // sum = (가격 * 수량) 들의 합
        }
        takeoutOrder.setStatus(TakeoutOrderStatus.ACCEPTED);
        return takeoutOrder;
    }

    @Transactional
    public TakeoutOrder serve(final UUID orderId) {
        final TakeoutOrder takeoutOrder = takeoutOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (takeoutOrder.getStatus() != TakeoutOrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        takeoutOrder.setStatus(TakeoutOrderStatus.SERVED);
        return takeoutOrder;
    }

    @Transactional
    public TakeoutOrder startDelivery(final UUID orderId) {
        final TakeoutOrder takeoutOrder = takeoutOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (takeoutOrder.getType() != TakeoutOrderType.DELIVERY) {
            throw new IllegalStateException();
        }
        if (takeoutOrder.getStatus() != TakeoutOrderStatus.SERVED) {
            throw new IllegalStateException();
        }
        takeoutOrder.setStatus(TakeoutOrderStatus.DELIVERING);
        return takeoutOrder;
    }

    @Transactional
    public TakeoutOrder completeDelivery(final UUID orderId) {
        final TakeoutOrder takeoutOrder = takeoutOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (takeoutOrder.getStatus() != TakeoutOrderStatus.DELIVERING) {
            throw new IllegalStateException();
        }
        takeoutOrder.setStatus(TakeoutOrderStatus.DELIVERED);
        return takeoutOrder;
    }

    @Transactional
    public TakeoutOrder complete(final UUID orderId) {
        final TakeoutOrder takeoutOrder = takeoutOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        final TakeoutOrderType type = takeoutOrder.getType();
        final TakeoutOrderStatus status = takeoutOrder.getStatus();
        if (type == TakeoutOrderType.DELIVERY) {
            if (status != TakeoutOrderStatus.DELIVERED) {
                throw new IllegalStateException();
            }
        }
        if (type == TakeoutOrderType.TAKEOUT || type == TakeoutOrderType.EAT_IN) {
            if (status != TakeoutOrderStatus.SERVED) {
                throw new IllegalStateException();
            }
        }
        takeoutOrder.setStatus(TakeoutOrderStatus.COMPLETED);
        return takeoutOrder;
    }

    @Transactional(readOnly = true)
    public List<TakeoutOrder> findAll() {
        return takeoutOrderRepository.findAll();
    }
}
