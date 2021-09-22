package tobe.kitchenpos.order.delivery.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tobe.kitchenpos.menu.menu.domain.Menu;
import tobe.kitchenpos.menu.menu.domain.MenuRepository;
import tobe.kitchenpos.order.delivery.domain.DeliveryOrderRepository;
import tobe.kitchenpos.order.common.domain.Order;
import tobe.kitchenpos.order.common.domain.OrderLineItem;
import tobe.kitchenpos.order.common.domain.OrderStatus;
import tobe.kitchenpos.order.common.domain.OrderType;
import tobe.kitchenpos.order.delivery.infra.KitchenridersClient;

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
    public Order create(final Order request) {
        final OrderType type = request.getType();
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException();
        }
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
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setType(type);
        order.setStatus(OrderStatus.WAITING);
        order.setOrderDateTime(LocalDateTime.now());
        order.setOrderLineItems(orderLineItems);

        final String deliveryAddress = request.getDeliveryAddress();
        if (Objects.isNull(deliveryAddress) || deliveryAddress.isEmpty()) {
            throw new IllegalArgumentException();
        }
        order.setDeliveryAddress(deliveryAddress);

        return deliveryOrderRepository.save(order);
    }

    @Transactional
    public Order accept(final UUID orderId) {
        final Order order = deliveryOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != OrderStatus.WAITING) {
            throw new IllegalStateException();
        }

        BigDecimal sum = BigDecimal.ZERO;
        for (final OrderLineItem orderLineItem : order.getOrderLineItems()) {
            sum = orderLineItem.getMenu()
                    .getPrice()
                    .multiply(BigDecimal.valueOf(orderLineItem.getQuantity()));
        }
        kitchenridersClient.requestDelivery(orderId, sum, order.getDeliveryAddress());
        order.setStatus(OrderStatus.ACCEPTED);

        return order;
    }

    @Transactional
    public Order serve(final UUID orderId) {
        final Order order = deliveryOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != OrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        order.setStatus(OrderStatus.SERVED);
        return order;
    }

    @Transactional
    public Order startDelivery(final UUID orderId) {
        final Order order = deliveryOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getType() != OrderType.DELIVERY) {
            throw new IllegalStateException();
        }
        if (order.getStatus() != OrderStatus.SERVED) {
            throw new IllegalStateException();
        }
        order.setStatus(OrderStatus.DELIVERING);
        return order;
    }

    @Transactional
    public Order completeDelivery(final UUID orderId) {
        final Order order = deliveryOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != OrderStatus.DELIVERING) {
            throw new IllegalStateException();
        }
        order.setStatus(OrderStatus.DELIVERED);
        return order;
    }

    @Transactional
    public Order complete(final UUID orderId) {
        final Order order = deliveryOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        final OrderStatus status = order.getStatus();
        if (status != OrderStatus.DELIVERED) {
            throw new IllegalStateException();
        }
        order.setStatus(OrderStatus.COMPLETED);

        return order;
    }

    @Transactional(readOnly = true)
    public List<Order> findAll() {
        deliveryOrderRepository.findByOrderType(OrderType.DELIVERY);

        return deliveryOrderRepository.findAll();
    }
}
