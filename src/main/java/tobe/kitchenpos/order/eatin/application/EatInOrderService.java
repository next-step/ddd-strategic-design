package tobe.kitchenpos.order.eatin.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tobe.kitchenpos.menu.menu.domain.Menu;
import tobe.kitchenpos.menu.menu.domain.MenuRepository;
import tobe.kitchenpos.order.common.domain.*;
import tobe.kitchenpos.order.eatin.domain.EatInOrderRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EatInOrderService {
    private final EatInOrderRepository eatInOrderRepository;
    private final MenuRepository menuRepository;
    private final OrderTableRepository orderTableRepository;

    public EatInOrderService(
        final EatInOrderRepository eatInOrderRepository,
        final MenuRepository menuRepository,
        final OrderTableRepository orderTableRepository
    ) {
        this.eatInOrderRepository = eatInOrderRepository;
        this.menuRepository = menuRepository;
        this.orderTableRepository = orderTableRepository;
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

        final OrderTable orderTable = orderTableRepository.findById(request.getOrderTableId())
                .orElseThrow(NoSuchElementException::new);
        if (orderTable.isEmpty()) {
            throw new IllegalStateException();
        }
        order.setOrderTable(orderTable);

        return eatInOrderRepository.save(order);
    }

    @Transactional
    public Order accept(final UUID orderId) {
        final Order order = eatInOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != OrderStatus.WAITING) {
            throw new IllegalStateException();
        }

        order.setStatus(OrderStatus.ACCEPTED);
        return order;
    }

    @Transactional
    public Order serve(final UUID orderId) {
        final Order order = eatInOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != OrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        order.setStatus(OrderStatus.SERVED);
        return order;
    }

    @Transactional
    public Order complete(final UUID orderId) {
        final Order order = eatInOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        final OrderStatus status = order.getStatus();
        if (status != OrderStatus.SERVED) {
            throw new IllegalStateException();
        }

        order.setStatus(OrderStatus.COMPLETED);
        final OrderTable orderTable = order.getOrderTable();
        if (!eatInOrderRepository.existsByOrderTableAndStatusNot(orderTable, OrderStatus.COMPLETED)) {
            orderTable.setNumberOfGuests(0);
            orderTable.setEmpty(true);
        }

        return order;
    }

    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return eatInOrderRepository.findAll();
    }
}
