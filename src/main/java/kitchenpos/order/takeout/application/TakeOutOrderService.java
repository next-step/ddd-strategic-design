package kitchenpos.order.takeout.application;

import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.order.common.domain.OrderLineItem;
import kitchenpos.order.common.domain.OrderType;
import kitchenpos.order.takeout.domain.TakeOutOrder;
import kitchenpos.order.takeout.domain.TakeOutOrderRepository;
import kitchenpos.order.takeout.domain.TakeOutOrderStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TakeOutOrderService {
    private final TakeOutOrderRepository orderRepository;
    private final MenuRepository menuRepository;

    public TakeOutOrderService(
        final TakeOutOrderRepository orderRepository,
        final MenuRepository menuRepository
    ) {
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
    }

    @Transactional
    public TakeOutOrder create(final TakeOutOrder request) {
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
        TakeOutOrder order = new TakeOutOrder();
        order.setId(UUID.randomUUID());
        order.setType(type);
        order.setStatus(TakeOutOrderStatus.WAITING);
        order.setOrderDateTime(LocalDateTime.now());
        order.setOrderLineItems(orderLineItems);

        return orderRepository.save(order);
    }

    @Transactional
    public TakeOutOrder accept(final UUID orderId) {
        final TakeOutOrder order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != TakeOutOrderStatus.WAITING) {
            throw new IllegalStateException();
        }
        order.setStatus(TakeOutOrderStatus.ACCEPTED);
        return order;
    }

    @Transactional
    public TakeOutOrder serve(final UUID orderId) {
        final TakeOutOrder order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != TakeOutOrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        order.setStatus(TakeOutOrderStatus.SERVED);
        return order;
    }

    @Transactional
    public TakeOutOrder complete(final UUID orderId) {
        final TakeOutOrder order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        final TakeOutOrderStatus status = order.getStatus();

        if (status != TakeOutOrderStatus.SERVED) {
            throw new IllegalStateException();
        }

        order.setStatus(TakeOutOrderStatus.COMPLETED);
        return order;
    }

    @Transactional(readOnly = true)
    public List<TakeOutOrder> findAll() {
        return orderRepository.findAll();
    }
}
