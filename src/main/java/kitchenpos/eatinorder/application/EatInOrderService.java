package kitchenpos.eatinorder.application;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import kitchenpos.eatinorder.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EatInOrderService {
    private final EatInOrderRepository eatInOrderRepository;
    private final OrderTableRepository orderTableRepository;

    public EatInOrderService(
        final EatInOrderRepository eatInOrderRepository,
        final OrderTableRepository orderTableRepository
    ) {
        this.eatInOrderRepository = eatInOrderRepository;
        this.orderTableRepository = orderTableRepository;
    }

    @Transactional
    public EatInOrder create(final EatInOrder request) {
        final EatInOrderType type = request.getType();
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException();
        }
        final List<EatInOrderLineItem> eatInOrderLineItemRequests = request.getOrderLineItems();
        if (Objects.isNull(eatInOrderLineItemRequests) || eatInOrderLineItemRequests.isEmpty()) {
            throw new IllegalArgumentException();
        }

        // 주문한 메뉴가 실존하는지 검증

        final List<EatInOrderLineItem> eatInOrderLineItems = new ArrayList<>();
        for (final EatInOrderLineItem eatInOrderLineItemRequest : eatInOrderLineItemRequests) {
            final long quantity = eatInOrderLineItemRequest.getQuantity();
            if (type != EatInOrderType.EAT_IN) {
                if (quantity < 0) {
                    throw new IllegalArgumentException();
                }
            }

            // 메뉴가 전시 중인지 검증

            // 메뉴의 현재 가격와 주문 금액이 일치하는지

            final EatInOrderLineItem eatInOrderLineItem = new EatInOrderLineItem();
            eatInOrderLineItem.setQuantity(quantity);
            eatInOrderLineItems.add(eatInOrderLineItem);
        }
        EatInOrder eatInOrder = new EatInOrder();
        eatInOrder.setId(UUID.randomUUID());
        eatInOrder.setType(type);
        eatInOrder.setStatus(EatInOrderStatus.WAITING);
        eatInOrder.setOrderDateTime(LocalDateTime.now());
        eatInOrder.setOrderLineItems(eatInOrderLineItems);
        if (type == EatInOrderType.DELIVERY) {
            final String deliveryAddress = request.getDeliveryAddress();
            if (Objects.isNull(deliveryAddress) || deliveryAddress.isEmpty()) {
                throw new IllegalArgumentException();
            }
            eatInOrder.setDeliveryAddress(deliveryAddress);
        }
        if (type == EatInOrderType.EAT_IN) {
            final OrderTable orderTable = orderTableRepository.findById(request.getEatInOrderTableId())
                .orElseThrow(NoSuchElementException::new);
            if (!orderTable.isOccupied()) {
                throw new IllegalStateException();
            }
            eatInOrder.setOrderTable(orderTable);
        }
        return eatInOrderRepository.save(eatInOrder);
    }

    @Transactional
    public EatInOrder accept(final UUID orderId) {
        final EatInOrder eatInOrder = eatInOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (eatInOrder.getStatus() != EatInOrderStatus.WAITING) {
            throw new IllegalStateException();
        }
        if (eatInOrder.getType() == EatInOrderType.DELIVERY) {
            BigDecimal sum = BigDecimal.ZERO;

            // sum = (가격 * 수량) 들의 합
        }
        eatInOrder.setStatus(EatInOrderStatus.ACCEPTED);
        return eatInOrder;
    }

    @Transactional
    public EatInOrder serve(final UUID orderId) {
        final EatInOrder eatInOrder = eatInOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (eatInOrder.getStatus() != EatInOrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        eatInOrder.setStatus(EatInOrderStatus.SERVED);
        return eatInOrder;
    }

    @Transactional
    public EatInOrder startDelivery(final UUID orderId) {
        final EatInOrder eatInOrder = eatInOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (eatInOrder.getType() != EatInOrderType.DELIVERY) {
            throw new IllegalStateException();
        }
        if (eatInOrder.getStatus() != EatInOrderStatus.SERVED) {
            throw new IllegalStateException();
        }
        eatInOrder.setStatus(EatInOrderStatus.DELIVERING);
        return eatInOrder;
    }

    @Transactional
    public EatInOrder completeDelivery(final UUID orderId) {
        final EatInOrder eatInOrder = eatInOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (eatInOrder.getStatus() != EatInOrderStatus.DELIVERING) {
            throw new IllegalStateException();
        }
        eatInOrder.setStatus(EatInOrderStatus.DELIVERED);
        return eatInOrder;
    }

    @Transactional
    public EatInOrder complete(final UUID orderId) {
        final EatInOrder eatInOrder = eatInOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        final EatInOrderType type = eatInOrder.getType();
        final EatInOrderStatus status = eatInOrder.getStatus();
        if (type == EatInOrderType.DELIVERY) {
            if (status != EatInOrderStatus.DELIVERED) {
                throw new IllegalStateException();
            }
        }
        if (type == EatInOrderType.TAKEOUT || type == EatInOrderType.EAT_IN) {
            if (status != EatInOrderStatus.SERVED) {
                throw new IllegalStateException();
            }
        }
        eatInOrder.setStatus(EatInOrderStatus.COMPLETED);
        if (type == EatInOrderType.EAT_IN) {
            final OrderTable orderTable = eatInOrder.getOrderTable();
            if (!eatInOrderRepository.existsByOrderTableAndStatusNot(orderTable, EatInOrderStatus.COMPLETED)) {
                orderTable.setNumberOfGuests(0);
                orderTable.setOccupied(false);
            }
        }
        return eatInOrder;
    }

    @Transactional(readOnly = true)
    public List<EatInOrder> findAll() {
        return eatInOrderRepository.findAll();
    }
}
