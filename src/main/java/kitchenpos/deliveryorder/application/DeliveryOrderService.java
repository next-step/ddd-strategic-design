package kitchenpos.deliveryorder.application;

import kitchenpos.deliveryorder.domain.DeliveryOrder;
import kitchenpos.deliveryorder.domain.DeliveryOrderLineItem;
import kitchenpos.deliveryorder.domain.DeliveryOrderRepository;
import kitchenpos.deliveryorder.domain.DeliveryOrderStatus;
import kitchenpos.shared.domain.OrderType;
import kitchenpos.deliveryorder.domain.KitchenRiders;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class DeliveryOrderService {
    private final DeliveryOrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final KitchenRiders kitchenRiders;

    public DeliveryOrderService(
            final DeliveryOrderRepository deliveryOrderRepository,
            final MenuRepository menuRepository,
            final KitchenRiders kitchenRiders
    ) {
        this.orderRepository = deliveryOrderRepository;
        this.menuRepository = menuRepository;
        this.kitchenRiders = kitchenRiders;
    }

    @Transactional
    public DeliveryOrder create(final DeliveryOrder request) {
        final OrderType type = request.getType();
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException();
        }
        final List<DeliveryOrderLineItem> orderLineItemRequests = request.getOrderLineItems();
        if (Objects.isNull(orderLineItemRequests) || orderLineItemRequests.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final List<Menu> menus = menuRepository.findAllByIdIn(
                orderLineItemRequests.stream()
                        .map(DeliveryOrderLineItem::getMenuId)
                        .toList()
        );
        if (menus.size() != orderLineItemRequests.size()) {
            throw new IllegalArgumentException();
        }
        final List<DeliveryOrderLineItem> orderLineItems = new ArrayList<>();
        for (final DeliveryOrderLineItem orderLineItemRequest : orderLineItemRequests) {
            final long quantity = orderLineItemRequest.getQuantity();
            if (type == OrderType.DELIVERY) {
                if (quantity < 0) {
                    throw new IllegalArgumentException();
                }
            }
            final Menu menu = menuRepository.findById(orderLineItemRequest.getMenuId())
                    .orElseThrow(NoSuchElementException::new);
            if (!menu.isDisplayed()) {
                throw new IllegalStateException();
            }
            if (menu.getPrice().compareTo(orderLineItemRequest.getPrice()) != 0) {
                throw new IllegalArgumentException();
            }
            final DeliveryOrderLineItem orderLineItem = new DeliveryOrderLineItem();
            orderLineItem.setMenu(menu);
            orderLineItem.setQuantity(quantity);
            orderLineItems.add(orderLineItem);
        }
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setId(UUID.randomUUID());
        deliveryOrder.setType(type);
        deliveryOrder.setStatus(DeliveryOrderStatus.WAITING);
        deliveryOrder.setOrderDateTime(LocalDateTime.now());
        deliveryOrder.setOrderLineItems(orderLineItems);
        if (type == OrderType.DELIVERY) {
            final String deliveryAddress = request.getDeliveryAddress();
            if (Objects.isNull(deliveryAddress) || deliveryAddress.isEmpty()) {
                throw new IllegalArgumentException();
            }
            deliveryOrder.setDeliveryAddress(deliveryAddress);
        }
        return orderRepository.save(deliveryOrder);
    }

    @Transactional
    public DeliveryOrder accept(final UUID deliveryOrderId) {
        final DeliveryOrder deliveryOrder = orderRepository.findById(deliveryOrderId)
                .orElseThrow(NoSuchElementException::new);
        if (deliveryOrder.getStatus() != DeliveryOrderStatus.WAITING) {
            throw new IllegalStateException();
        }
        if (deliveryOrder.getType() == OrderType.DELIVERY) {
            BigDecimal sum = BigDecimal.ZERO;
            for (final DeliveryOrderLineItem orderLineItem : deliveryOrder.getOrderLineItems()) {
                sum = orderLineItem.getMenu()
                        .getPrice()
                        .multiply(BigDecimal.valueOf(orderLineItem.getQuantity()));
            }
            kitchenRiders.requestDelivery(deliveryOrderId, sum, deliveryOrder.getDeliveryAddress());
        }
        deliveryOrder.setStatus(DeliveryOrderStatus.ACCEPTED);
        return deliveryOrder;
    }

    @Transactional
    public DeliveryOrder serve(final UUID deliveryOrderId) {
        final DeliveryOrder deliveryOrder = orderRepository.findById(deliveryOrderId)
                .orElseThrow(NoSuchElementException::new);
        if (deliveryOrder.getStatus() != DeliveryOrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        deliveryOrder.setStatus(DeliveryOrderStatus.SERVED);
        return deliveryOrder;
    }

    @Transactional
    public DeliveryOrder startDelivery(final UUID deliveryOrderId) {
        final DeliveryOrder deliveryOrder = orderRepository.findById(deliveryOrderId)
                .orElseThrow(NoSuchElementException::new);
        if (deliveryOrder.getType() != OrderType.DELIVERY) {
            throw new IllegalStateException();
        }
        if (deliveryOrder.getStatus() != DeliveryOrderStatus.SERVED) {
            throw new IllegalStateException();
        }
        deliveryOrder.setStatus(DeliveryOrderStatus.DELIVERING);
        return deliveryOrder;
    }

    @Transactional
    public DeliveryOrder completeDelivery(final UUID deliveryOrderId) {
        final DeliveryOrder deliveryOrder = orderRepository.findById(deliveryOrderId)
                .orElseThrow(NoSuchElementException::new);
        if (deliveryOrder.getStatus() != DeliveryOrderStatus.DELIVERING) {
            throw new IllegalStateException();
        }
        deliveryOrder.setStatus(DeliveryOrderStatus.DELIVERED);
        return deliveryOrder;
    }

    @Transactional
    public DeliveryOrder complete(final UUID deliveryOrderId) {
        final DeliveryOrder deliveryOrder = orderRepository.findById(deliveryOrderId)
                .orElseThrow(NoSuchElementException::new);
        final OrderType type = deliveryOrder.getType();
        final DeliveryOrderStatus status = deliveryOrder.getStatus();
        if (type == OrderType.DELIVERY) {
            if (status != DeliveryOrderStatus.DELIVERED) {
                throw new IllegalStateException();
            }
        }
        deliveryOrder.setStatus(DeliveryOrderStatus.COMPLETED);
        return deliveryOrder;
    }

    @Transactional(readOnly = true)
    public List<DeliveryOrder> findAll() {
        return orderRepository.findAll();
    }
}
