package kitchenpos.deliveryorder.application;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import kitchenpos.deliveryorder.domain.*;
import kitchenpos.deliveryorder.infra.KitchenridersClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliveryOrderService {
    private final DeliveryOrderRepository deliveryOrderRepository;
    private final KitchenridersClient kitchenridersClient;

    public DeliveryOrderService(
        final DeliveryOrderRepository deliveryOrderRepository,
        final KitchenridersClient kitchenridersClient
    ) {
        this.deliveryOrderRepository = deliveryOrderRepository;
        this.kitchenridersClient = kitchenridersClient;
    }

    @Transactional
    public DeliveryOrder create(final DeliveryOrder request) {
        final DeliveryOrderType type = request.getType();
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException();
        }
        final List<DeliveryOrderLineItem> deliveryOrderLineItemRequests = request.getOrderLineItems();
        if (Objects.isNull(deliveryOrderLineItemRequests) || deliveryOrderLineItemRequests.isEmpty()) {
            throw new IllegalArgumentException();
        }

        // 주문한 메뉴가 실존하는지 검증

        final List<DeliveryOrderLineItem> deliveryOrderLineItems = new ArrayList<>();
        for (final DeliveryOrderLineItem deliveryOrderLineItemRequest : deliveryOrderLineItemRequests) {
            final long quantity = deliveryOrderLineItemRequest.getQuantity();
            if (type != DeliveryOrderType.EAT_IN) {
                if (quantity < 0) {
                    throw new IllegalArgumentException();
                }
            }

            // 메뉴가 전시 중인지 검증

            // 메뉴의 현재 가격와 주문 금액이 일치하는지

            final DeliveryOrderLineItem deliveryOrderLineItem = new DeliveryOrderLineItem();
            deliveryOrderLineItem.setQuantity(quantity);
            deliveryOrderLineItems.add(deliveryOrderLineItem);
        }
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setId(UUID.randomUUID());
        deliveryOrder.setType(type);
        deliveryOrder.setStatus(DeliveryOrderStatus.WAITING);
        deliveryOrder.setOrderDateTime(LocalDateTime.now());
        deliveryOrder.setOrderLineItems(deliveryOrderLineItems);
        if (type == DeliveryOrderType.DELIVERY) {
            final String deliveryAddress = request.getDeliveryAddress();
            if (Objects.isNull(deliveryAddress) || deliveryAddress.isEmpty()) {
                throw new IllegalArgumentException();
            }
            deliveryOrder.setDeliveryAddress(deliveryAddress);
        }
        return deliveryOrderRepository.save(deliveryOrder);
    }

    @Transactional
    public DeliveryOrder accept(final UUID orderId) {
        final DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (deliveryOrder.getStatus() != DeliveryOrderStatus.WAITING) {
            throw new IllegalStateException();
        }
        if (deliveryOrder.getType() == DeliveryOrderType.DELIVERY) {
            BigDecimal sum = BigDecimal.ZERO;

            // sum = (가격 * 수량) 들의 합

            kitchenridersClient.requestDelivery(orderId, sum, deliveryOrder.getDeliveryAddress());
        }
        deliveryOrder.setStatus(DeliveryOrderStatus.ACCEPTED);
        return deliveryOrder;
    }

    @Transactional
    public DeliveryOrder serve(final UUID orderId) {
        final DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (deliveryOrder.getStatus() != DeliveryOrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        deliveryOrder.setStatus(DeliveryOrderStatus.SERVED);
        return deliveryOrder;
    }

    @Transactional
    public DeliveryOrder startDelivery(final UUID orderId) {
        final DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (deliveryOrder.getType() != DeliveryOrderType.DELIVERY) {
            throw new IllegalStateException();
        }
        if (deliveryOrder.getStatus() != DeliveryOrderStatus.SERVED) {
            throw new IllegalStateException();
        }
        deliveryOrder.setStatus(DeliveryOrderStatus.DELIVERING);
        return deliveryOrder;
    }

    @Transactional
    public DeliveryOrder completeDelivery(final UUID orderId) {
        final DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (deliveryOrder.getStatus() != DeliveryOrderStatus.DELIVERING) {
            throw new IllegalStateException();
        }
        deliveryOrder.setStatus(DeliveryOrderStatus.DELIVERED);
        return deliveryOrder;
    }

    @Transactional
    public DeliveryOrder complete(final UUID orderId) {
        final DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        final DeliveryOrderType type = deliveryOrder.getType();
        final DeliveryOrderStatus status = deliveryOrder.getStatus();
        if (type == DeliveryOrderType.DELIVERY) {
            if (status != DeliveryOrderStatus.DELIVERED) {
                throw new IllegalStateException();
            }
        }
        if (type == DeliveryOrderType.TAKEOUT || type == DeliveryOrderType.EAT_IN) {
            if (status != DeliveryOrderStatus.SERVED) {
                throw new IllegalStateException();
            }
        }
        deliveryOrder.setStatus(DeliveryOrderStatus.COMPLETED);
        return deliveryOrder;
    }

    @Transactional(readOnly = true)
    public List<DeliveryOrder> findAll() {
        return deliveryOrderRepository.findAll();
    }
}
