package kitchenpos.takeoutorder.application;

import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.takeoutorder.domain.TakeOutOrder;
import kitchenpos.takeoutorder.domain.TakeOutOrderLineItem;
import kitchenpos.takeoutorder.domain.TakeOutOrderRepository;
import kitchenpos.takeoutorder.domain.TakeOutOrderStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static kitchenpos.takeoutorder.domain.TakeOutOrderStatus.WAITING;

@Service
public class TakeOutOrderService {
    private final TakeOutOrderRepository takeOutOrderRepository;
    private final MenuRepository menuRepository;

    public TakeOutOrderService(
            final TakeOutOrderRepository takeOutOrderRepository,
            final MenuRepository menuRepository
    ) {
        this.takeOutOrderRepository = takeOutOrderRepository;
        this.menuRepository = menuRepository;
    }

    @Transactional
    public TakeOutOrder create(final TakeOutOrder request) {
        final List<TakeOutOrderLineItem> orderLineItemRequests = request.getTakeOutOrderLineItems();
        if (Objects.isNull(orderLineItemRequests) || orderLineItemRequests.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final List<Menu> menus = menuRepository.findAllByIdIn(
                orderLineItemRequests.stream()
                        .map(TakeOutOrderLineItem::getMenuId)
                        .collect(Collectors.toList())
        );
        if (menus.size() != orderLineItemRequests.size()) {
            throw new IllegalArgumentException();
        }
        final List<TakeOutOrderLineItem> orderLineItems = new ArrayList<>();
        for (final TakeOutOrderLineItem orderLineItemRequest : orderLineItemRequests) {
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
            final TakeOutOrderLineItem orderLineItem = new TakeOutOrderLineItem();
            orderLineItem.setMenu(menu);
            orderLineItem.setQuantity(quantity);
            orderLineItems.add(orderLineItem);
        }
        TakeOutOrder order = new TakeOutOrder();
        order.setId(UUID.randomUUID());
        order.setStatus(WAITING);
        order.setOrderDateTime(LocalDateTime.now());
        order.setTakeOutOrderLineItems(orderLineItems);
        return takeOutOrderRepository.save(order);
    }

    @Transactional
    public TakeOutOrder accept(final UUID orderId) {
        final TakeOutOrder order = takeOutOrderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != WAITING) {
            throw new IllegalStateException();
        }
        order.setStatus(TakeOutOrderStatus.ACCEPTED);
        return order;
    }

    @Transactional
    public TakeOutOrder serve(final UUID orderId) {
        final TakeOutOrder order = takeOutOrderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != TakeOutOrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        order.setStatus(TakeOutOrderStatus.SERVED);
        return order;
    }

    @Transactional
    public TakeOutOrder complete(final UUID orderId) {
        final TakeOutOrder order = takeOutOrderRepository.findById(orderId)
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
        return takeOutOrderRepository.findAll();
    }
}
