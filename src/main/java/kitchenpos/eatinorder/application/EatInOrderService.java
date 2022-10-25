package kitchenpos.eatinorder.application;

import kitchenpos.eatinorder.domain.*;
import kitchenpos.eatinorder.repository.EatInOrderRepository;
import kitchenpos.eatinorder.repository.OrderTableRepository;
import kitchenpos.menu.menu.domain.Menu;
import kitchenpos.menu.menu.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public EatInOrder create(final EatInOrder request) {
        final OrderType type = request.getType();
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException();
        }
        final List<EatInOrderLineItem> eatInOrderLineItemRequests = request.getOrderLineItems();
        if (Objects.isNull(eatInOrderLineItemRequests) || eatInOrderLineItemRequests.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final List<Menu> menus = menuRepository.findAllByIdIn(
            eatInOrderLineItemRequests.stream()
                .map(EatInOrderLineItem::getMenuId)
                .collect(Collectors.toList())
        );
        if (menus.size() != eatInOrderLineItemRequests.size()) {
            throw new IllegalArgumentException();
        }
        final List<EatInOrderLineItem> eatInOrderLineItems = new ArrayList<>();
        for (final EatInOrderLineItem eatInOrderLineItemRequest : eatInOrderLineItemRequests) {
            final long quantity = eatInOrderLineItemRequest.getQuantity();
            if (type != OrderType.EAT_IN) {
                if (quantity < 0) {
                    throw new IllegalArgumentException();
                }
            }
            final Menu menu = menuRepository.findById(eatInOrderLineItemRequest.getMenuId())
                .orElseThrow(NoSuchElementException::new);
            if (!menu.isDisplayed()) {
                throw new IllegalStateException();
            }
            if (menu.getPrice().compareTo(eatInOrderLineItemRequest.getPrice()) != 0) {
                throw new IllegalArgumentException();
            }
            final EatInOrderLineItem eatInOrderLineItem = new EatInOrderLineItem();
            eatInOrderLineItem.setMenu(menu);
            eatInOrderLineItem.setQuantity(quantity);
            eatInOrderLineItems.add(eatInOrderLineItem);
        }
        EatInOrder eatInOrder = new EatInOrder();
        eatInOrder.setId(UUID.randomUUID());
        eatInOrder.setType(type);
        eatInOrder.setStatus(OrderStatus.WAITING);
        eatInOrder.setOrderDateTime(LocalDateTime.now());
        eatInOrder.setOrderLineItems(eatInOrderLineItems);
        if (type == OrderType.DELIVERY) {
            final String deliveryAddress = request.getDeliveryAddress();
            if (Objects.isNull(deliveryAddress) || deliveryAddress.isEmpty()) {
                throw new IllegalArgumentException();
            }
            eatInOrder.setDeliveryAddress(deliveryAddress);
        }
        if (type == OrderType.EAT_IN) {
            final OrderTable orderTable = orderTableRepository.findById(request.getOrderTableId())
                .orElseThrow(NoSuchElementException::new);
            if (!orderTable.isOccupied()) {
                throw new IllegalStateException();
            }
            eatInOrder.setOrderTable(orderTable);
        }
        return eatInOrderRepository.save(eatInOrder);
    }

    @Transactional
    public EatInOrder accept(final UUID orderId) {
        final EatInOrder eatInOrder = eatInOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (eatInOrder.getStatus() != OrderStatus.WAITING) {
            throw new IllegalStateException();
        }
        eatInOrder.setStatus(OrderStatus.ACCEPTED);
        return eatInOrder;
    }

    @Transactional
    public EatInOrder serve(final UUID orderId) {
        final EatInOrder eatInOrder = eatInOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (eatInOrder.getStatus() != OrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        eatInOrder.setStatus(OrderStatus.SERVED);
        return eatInOrder;
    }

    @Transactional
    public EatInOrder complete(final UUID orderId) {
        final EatInOrder eatInOrder = eatInOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        final OrderType type = eatInOrder.getType();
        final OrderStatus status = eatInOrder.getStatus();
        if (status != OrderStatus.SERVED) {
            throw new IllegalStateException();
        }
        eatInOrder.setStatus(OrderStatus.COMPLETED);
        if (type == OrderType.EAT_IN) {
            final OrderTable orderTable = eatInOrder.getOrderTable();
            if (!eatInOrderRepository.existsByOrderTableAndStatusNot(orderTable, OrderStatus.COMPLETED)) {
                orderTable.setNumberOfGuests(0);
                orderTable.setOccupied(false);
            }
        }
        return eatInOrder;
    }

    @Transactional(readOnly = true)
    public List<EatInOrder> findAll() {
        return eatInOrderRepository.findAll();
    }
}
