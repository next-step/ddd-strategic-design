package kitchenpos.order.orderEatIn.application;


import kitchenpos.order.orderEatIn.domain.*;
import kitchenpos.order.orderCommonDomain.OrderLineItem;
import kitchenpos.orderEatIn.domain.*;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderEatInService {
    private final OrderEatInRepository orderRepository;
    private final MenuRepository menuRepository;
    private final OrderTableRepository orderTableRepository;

    public OrderEatInService(
        final OrderEatInRepository orderRepository,
        final MenuRepository menuRepository,
        final OrderTableRepository orderTableRepository
    ) {
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
        this.orderTableRepository = orderTableRepository;
    }

    @Transactional
    public OrderEatIn create(final OrderEatIn request) {
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
        OrderEatIn order = new OrderEatIn();
        order.setId(UUID.randomUUID());
        order.setStatus(OrderEatInStatus.WAITING);
        order.setOrderDateTime(LocalDateTime.now());
        order.setOrderLineItems(orderLineItems);

        final OrderTable orderTable = orderTableRepository.findById(request.getOrderTableId())
            .orElseThrow(NoSuchElementException::new);
        if (!orderTable.isOccupied()) {
            throw new IllegalStateException();
        }
        order.setOrderTable(orderTable);
        return orderRepository.save(order);
    }

    @Transactional
    public OrderEatIn accept(final UUID orderId) {
        final OrderEatIn order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != OrderEatInStatus.WAITING) {
            throw new IllegalStateException();
        }
        order.setStatus(OrderEatInStatus.ACCEPTED);
        return order;
    }

    @Transactional
    public OrderEatIn serve(final UUID orderId) {
        final OrderEatIn order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != OrderEatInStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        order.setStatus(OrderEatInStatus.SERVED);
        return order;
    }

    @Transactional
    public OrderEatIn complete(final UUID orderId) {
        final OrderEatIn order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        final OrderEatInStatus status = order.getStatus();
        if (status != OrderEatInStatus.SERVED) {
            throw new IllegalStateException();
        }
        order.setStatus(OrderEatInStatus.COMPLETED);
        final OrderTable orderTable = order.getOrderTable();
        if (!orderRepository.existsByOrderTableAndStatusNot(orderTable, OrderEatInStatus.COMPLETED)) {
            orderTable.setNumberOfGuests(0);
            orderTable.setOccupied(false);
        }
        return order;
    }

    @Transactional(readOnly = true)
    public List<OrderEatIn> findAll() {
        return orderRepository.findAll();
    }
}
