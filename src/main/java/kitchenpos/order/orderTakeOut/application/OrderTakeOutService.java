package kitchenpos.order.orderTakeOut.application;

import kitchenpos.order.orderCommonDomain.OrderLineItem;
import kitchenpos.order.orderTakeOut.domain.OrderTakeOut;
import kitchenpos.order.orderTakeOut.domain.OrderTakeOutRepository;
import kitchenpos.order.orderTakeOut.domain.OrderTakeOutStatus;
import kitchenpos.orderTakeOut.domain.*;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderTakeOutService {
    private final OrderTakeOutRepository orderRepository;
    private final MenuRepository menuRepository;

    public OrderTakeOutService(
        final OrderTakeOutRepository orderRepository,
        final MenuRepository menuRepository
    ) {
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
    }

    @Transactional
    public OrderTakeOut create(final OrderTakeOut request) {
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
        OrderTakeOut order = new OrderTakeOut();
        order.setId(UUID.randomUUID());
        order.setStatus(OrderTakeOutStatus.WAITING);
        order.setOrderDateTime(LocalDateTime.now());
        order.setOrderLineItems(orderLineItems);

        return orderRepository.save(order);
    }

    @Transactional
    public OrderTakeOut accept(final UUID orderId) {
        final OrderTakeOut order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != OrderTakeOutStatus.WAITING) {
            throw new IllegalStateException();
        }
        order.setStatus(OrderTakeOutStatus.ACCEPTED);
        return order;
    }

    @Transactional
    public OrderTakeOut serve(final UUID orderId) {
        final OrderTakeOut order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != OrderTakeOutStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        order.setStatus(OrderTakeOutStatus.SERVED);
        return order;
    }



    @Transactional
    public OrderTakeOut complete(final UUID orderId) {
        final OrderTakeOut order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        final OrderTakeOutStatus status = order.getStatus();
        if (status != OrderTakeOutStatus.SERVED) {
            throw new IllegalStateException();
        }
        order.setStatus(OrderTakeOutStatus.COMPLETED);
        return order;
    }

    @Transactional(readOnly = true)
    public List<OrderTakeOut> findAll() {
        return orderRepository.findAll();
    }
}
