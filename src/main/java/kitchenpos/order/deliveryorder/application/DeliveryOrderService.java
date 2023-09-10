package kitchenpos.order.deliveryorder.application;

import kitchenpos.order.deliveryorder.domain.DeliveryOrder;
import kitchenpos.order.deliveryorder.domain.DeliveryOrderRepository;
import kitchenpos.order.deliveryorder.domain.DeliveryOrderStatus;
import kitchenpos.order.deliveryorder.infra.KitchenridersClient;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.order.common.domain.OrderLineItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeliveryOrderService {
    private final DeliveryOrderRepository deliveryOrderRepository;
    private final MenuRepository menuRepository;
    private final KitchenridersClient kitchenridersClient;

    public DeliveryOrderService(
            final DeliveryOrderRepository deliveryOrderRepository,
            final MenuRepository menuRepository,
            final KitchenridersClient kitchenridersClient
    ) {
        this.deliveryOrderRepository = deliveryOrderRepository;
        this.menuRepository = menuRepository;
        this.kitchenridersClient = kitchenridersClient;
    }

    @Transactional
    public DeliveryOrder create(final DeliveryOrder request) {

        final List<OrderLineItem> orderLineItemRequests = request.getOrderLineItems();
        if (Objects.isNull(orderLineItemRequests) || orderLineItemRequests.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final List<Menu> menus = menuRepository.findAllByIdIn(
                orderLineItemRequests.stream()
                        .map(OrderLineItem::getMenuId)
                        .collect(Collectors.toList())
        );
        if (menus.size() != orderLineItemRequests.size()) {
            throw new IllegalArgumentException();
        }
        final List<OrderLineItem> orderLineItems = new ArrayList<>();
        for (final OrderLineItem orderLineItemRequest : orderLineItemRequests) {
            final long quantity = orderLineItemRequest.getQuantity();
            if (quantity < 0) {
                throw new IllegalArgumentException();
            }

            final Menu menu = menuRepository.findById(orderLineItemRequest.getMenuId())
                    .orElseThrow(NoSuchElementException::new);
            if (!menu.isDisplayed()) {
                throw new IllegalStateException();
            }
            if (menu.getPrice().compareTo(orderLineItemRequest.getPrice()) != 0) {
                throw new IllegalArgumentException();
            }
            final OrderLineItem orderLineItem = new OrderLineItem();
            orderLineItem.setMenu(menu);
            orderLineItem.setQuantity(quantity);
            orderLineItems.add(orderLineItem);
        }
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setId(UUID.randomUUID());
        deliveryOrder.setStatus(DeliveryOrderStatus.WAITING);
        deliveryOrder.setOrderDateTime(LocalDateTime.now());
        deliveryOrder.setOrderLineItems(orderLineItems);

        final String deliveryAddress = request.getDeliveryAddress();
        if (Objects.isNull(deliveryAddress) || deliveryAddress.isEmpty()) {
            throw new IllegalArgumentException();
        }
        deliveryOrder.setDeliveryAddress(deliveryAddress);

        return deliveryOrderRepository.save(deliveryOrder);
    }

    @Transactional
    public DeliveryOrder accept(final UUID orderId) {
        final DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);
        if (deliveryOrder.getStatus() != DeliveryOrderStatus.WAITING) {
            throw new IllegalStateException();
        }
        BigDecimal sum = BigDecimal.ZERO;
        for (final OrderLineItem orderLineItem : deliveryOrder.getOrderLineItems()) {
            sum = orderLineItem.getMenu()
                    .getPrice()
                    .multiply(BigDecimal.valueOf(orderLineItem.getQuantity()));
        }
        kitchenridersClient.requestDelivery(orderId, sum, deliveryOrder.getDeliveryAddress());
        deliveryOrder.setStatus(DeliveryOrderStatus.ACCEPTED);
        return deliveryOrder;
    }

    @Transactional
    public DeliveryOrder serve(final UUID orderId) {
        final DeliveryOrder order = deliveryOrderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != DeliveryOrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        order.setStatus(DeliveryOrderStatus.SERVED);
        return order;
    }

    @Transactional
    public DeliveryOrder startDelivery(final UUID orderId) {
        final DeliveryOrder order = deliveryOrderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != DeliveryOrderStatus.SERVED) {
            throw new IllegalStateException();
        }
        order.setStatus(DeliveryOrderStatus.DELIVERING);
        return order;
    }

    @Transactional
    public DeliveryOrder completeDelivery(final UUID orderId) {
        final DeliveryOrder order = deliveryOrderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != DeliveryOrderStatus.DELIVERING) {
            throw new IllegalStateException();
        }
        order.setStatus(DeliveryOrderStatus.DELIVERED);
        return order;
    }

    @Transactional
    public DeliveryOrder complete(final UUID orderId) {
        final DeliveryOrder order = deliveryOrderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);

        final DeliveryOrderStatus status = order.getStatus();
        if (status != DeliveryOrderStatus.DELIVERED) {
            throw new IllegalStateException();
        }

        order.setStatus(DeliveryOrderStatus.COMPLETED);

        return order;
    }

    @Transactional(readOnly = true)
    public List<DeliveryOrder> findAll() {
        return deliveryOrderRepository.findAll();
    }
}
