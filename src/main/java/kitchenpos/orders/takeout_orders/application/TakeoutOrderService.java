package kitchenpos.orders.takeout_orders.application;

import kitchenpos.menus.domain.Menu;
import kitchenpos.menus.domain.MenuRepository;
import kitchenpos.orders.domain.*;
import kitchenpos.orders.takeout_orders.domain.TakeoutOrder;
import kitchenpos.orders.takeout_orders.domain.TakeoutOrderLineItem;
import kitchenpos.orders.takeout_orders.domain.TakeoutOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TakeoutOrderService {
    private final TakeoutOrderRepository orderRepository;
    private final MenuRepository menuRepository;

    public TakeoutOrderService(
            final TakeoutOrderRepository orderRepository,
            final MenuRepository menuRepository
    ) {
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
    }

    @Transactional
    public TakeoutOrder create(final TakeoutOrder request) {
        final List<TakeoutOrderLineItem> orderLineItemRequests = request.getOrderLineItems();
        if (Objects.isNull(orderLineItemRequests) || orderLineItemRequests.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final List<Menu> menus = menuRepository.findAllByIdIn(
                orderLineItemRequests.stream()
                        .map(TakeoutOrderLineItem::getMenuId)
                        .collect(Collectors.toList())
        );
        if (menus.size() != orderLineItemRequests.size()) {
            throw new IllegalArgumentException();
        }
        final List<TakeoutOrderLineItem> orderLineItems = new ArrayList<>();
        for (final TakeoutOrderLineItem orderLineItemRequest : orderLineItemRequests) {
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
            final TakeoutOrderLineItem orderLineItem = new TakeoutOrderLineItem();
            orderLineItem.setMenu(menu);
            orderLineItem.setQuantity(quantity);
            orderLineItems.add(orderLineItem);
        }
        TakeoutOrder order = new TakeoutOrder();
        order.setId(UUID.randomUUID());
        order.setStatus(OrderStatus.WAITING);
        order.setOrderDateTime(LocalDateTime.now());
        order.setOrderLineItems(orderLineItems);
        return orderRepository.save(order);
    }

    @Transactional
    public TakeoutOrder accept(final UUID orderId) {
        final TakeoutOrder order = orderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != OrderStatus.WAITING) {
            throw new IllegalStateException();
        }
        order.setStatus(OrderStatus.ACCEPTED);
        return order;
    }

    @Transactional
    public TakeoutOrder serve(final UUID orderId) {
        final TakeoutOrder order = orderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != OrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        order.setStatus(OrderStatus.SERVED);
        return order;
    }

    @Transactional
    public TakeoutOrder complete(final UUID orderId) {
        final TakeoutOrder order = orderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);
        final OrderStatus status = order.getStatus();
        if (status != OrderStatus.SERVED) {
            throw new IllegalStateException();
        }
        order.setStatus(OrderStatus.COMPLETED);
        return order;
    }

    @Transactional(readOnly = true)
    public List<TakeoutOrder> findAll() {
        return orderRepository.findAll();
    }
}
