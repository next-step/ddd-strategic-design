package kitchenpos.order.orderDelivery.application;

import kitchenpos.order.orderCommonDomain.OrderLineItem;
import kitchenpos.order.orderDelivery.domain.OrderDelivery;
import kitchenpos.order.orderDelivery.domain.OrderDeliveryRepository;
import kitchenpos.order.orderDelivery.domain.OrderDeliveryStatus;
import kitchenpos.order.orderDelivery.infra.KitchenridersClient;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.orderDelivery.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderDeliveryService {
    private final OrderDeliveryRepository orderRepository;
    private final MenuRepository menuRepository;
    private final KitchenridersClient kitchenridersClient;

    public OrderDeliveryService(
        final OrderDeliveryRepository orderRepository,
        final MenuRepository menuRepository,
        final KitchenridersClient kitchenridersClient
    ) {
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
        this.kitchenridersClient = kitchenridersClient;
    }

    @Transactional
    public OrderDelivery create(final OrderDelivery request) {
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
        OrderDelivery order = new OrderDelivery();
        order.setId(UUID.randomUUID());
        order.setStatus(OrderDeliveryStatus.WAITING);
        order.setOrderDateTime(LocalDateTime.now());
        order.setOrderLineItems(orderLineItems);
        final String deliveryAddress = request.getDeliveryAddress();
        if (Objects.isNull(deliveryAddress) || deliveryAddress.isEmpty()) {
            throw new IllegalArgumentException();
        }
        order.setDeliveryAddress(deliveryAddress);

        return orderRepository.save(order);
    }

    @Transactional
    public OrderDelivery accept(final UUID orderId) {
        final OrderDelivery order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != OrderDeliveryStatus.WAITING) {
            throw new IllegalStateException();
        }
        BigDecimal sum = BigDecimal.ZERO;
        for (final OrderLineItem orderLineItem : order.getOrderLineItems()) {
            sum = orderLineItem.getMenu()
                .getPrice()
                .multiply(BigDecimal.valueOf(orderLineItem.getQuantity()));
        }
        kitchenridersClient.requestDelivery(orderId, sum, order.getDeliveryAddress());
        order.setStatus(OrderDeliveryStatus.ACCEPTED);
        return order;
    }

    @Transactional
    public OrderDelivery serve(final UUID orderId) {
        final OrderDelivery order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != OrderDeliveryStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        order.setStatus(OrderDeliveryStatus.SERVED);
        return order;
    }

    @Transactional
    public OrderDelivery startDelivery(final UUID orderId) {
        final OrderDelivery order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != OrderDeliveryStatus.SERVED) {
            throw new IllegalStateException();
        }
        order.setStatus(OrderDeliveryStatus.DELIVERING);
        return order;
    }

    @Transactional
    public OrderDelivery completeDelivery(final UUID orderId) {
        final OrderDelivery order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != OrderDeliveryStatus.DELIVERING) {
            throw new IllegalStateException();
        }
        order.setStatus(OrderDeliveryStatus.DELIVERED);
        return order;
    }

    @Transactional
    public OrderDelivery complete(final UUID orderId) {
        final OrderDelivery order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        final OrderDeliveryStatus status = order.getStatus();
        if (status != OrderDeliveryStatus.DELIVERED) {
            throw new IllegalStateException();
        }
        order.setStatus(OrderDeliveryStatus.COMPLETED);
        return order;
    }

    @Transactional(readOnly = true)
    public List<OrderDelivery> findAll() {
        return orderRepository.findAll();
    }
}
