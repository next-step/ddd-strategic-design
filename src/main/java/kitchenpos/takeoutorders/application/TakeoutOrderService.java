package kitchenpos.takeoutorders.application;

import kitchenpos.menus.domain.Menu;
import kitchenpos.menus.domain.MenuRepository;
import kitchenpos.takeoutorders.domain.*;
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
        final OrderType type = request.getType();
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException();
        }
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
        order.setType(type);
        order.setStatus(TakeoutOrderStatus.WAITING);
        order.setOrderDateTime(LocalDateTime.now());
        order.setOrderLineItems(orderLineItems);
        return orderRepository.save(order);
    }

    @Transactional
    public TakeoutOrder accept(final UUID orderId) {
        final TakeoutOrder order = orderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != TakeoutOrderStatus.WAITING) {
            throw new IllegalStateException();
        }
        order.setStatus(TakeoutOrderStatus.ACCEPTED);
        return order;
    }

    @Transactional
    public TakeoutOrder serve(final UUID orderId) {
        final TakeoutOrder order = orderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != TakeoutOrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        order.setStatus(TakeoutOrderStatus.SERVED);
        return order;
    }

    @Transactional
    public TakeoutOrder complete(final UUID orderId) {
        final TakeoutOrder order = orderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);
        final TakeoutOrderStatus status = order.getStatus();
        if (status != TakeoutOrderStatus.SERVED) {
            throw new IllegalStateException();
        }
        order.setStatus(TakeoutOrderStatus.COMPLETED);
        return order;
    }

    @Transactional(readOnly = true)
    public List<TakeoutOrder> findAll() {
        return orderRepository.findAll();
    }
}
