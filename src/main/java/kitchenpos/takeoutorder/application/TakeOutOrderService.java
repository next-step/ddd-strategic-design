package kitchenpos.takeoutorder.application;

import kitchenpos.common.domain.OrderLineItem;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.takeoutorder.domain.TakeOutOrder;
import kitchenpos.takeoutorder.domain.TakeOutOrderRepository;
import kitchenpos.takeoutorder.domain.TakeOutOrderStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
        TakeOutOrder takeOutOrder = new TakeOutOrder();
        takeOutOrder.setId(UUID.randomUUID());
        takeOutOrder.setStatus(TakeOutOrderStatus.WAITING);
        takeOutOrder.setOrderDateTime(LocalDateTime.now());
        takeOutOrder.setOrderLineItems(orderLineItems);

        return takeOutOrderRepository.save(takeOutOrder);
    }

    @Transactional
    public TakeOutOrder accept(final UUID orderId) {
        final TakeOutOrder takeOutOrder = takeOutOrderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);
        if (takeOutOrder.getStatus() != TakeOutOrderStatus.WAITING) {
            throw new IllegalStateException();
        }

        takeOutOrder.setStatus(TakeOutOrderStatus.ACCEPTED);
        return takeOutOrder;
    }

    @Transactional
    public TakeOutOrder serve(final UUID orderId) {
        final TakeOutOrder takeOutOrder = takeOutOrderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);
        if (takeOutOrder.getStatus() != TakeOutOrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        takeOutOrder.setStatus(TakeOutOrderStatus.SERVED);
        return takeOutOrder;
    }

    @Transactional
    public TakeOutOrder complete(final UUID orderId) {
        final TakeOutOrder takeOutOrder = takeOutOrderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);
        final TakeOutOrderStatus status = takeOutOrder.getStatus();

        if (status != TakeOutOrderStatus.SERVED) {
            throw new IllegalStateException();
        }
        takeOutOrder.setStatus(TakeOutOrderStatus.COMPLETED);

        return takeOutOrder;
    }

    @Transactional(readOnly = true)
    public List<TakeOutOrder> findAll() {
        return takeOutOrderRepository.findAll();
    }
}
