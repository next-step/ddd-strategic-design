package kitchenpos.takeout_order.application;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import kitchenpos.delivery_order.infra.KitchenridersClient;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.takeout_order.domain.TakeoutOrder;
import kitchenpos.takeout_order.domain.TakeoutOrderLineItem;
import kitchenpos.takeout_order.domain.TakeoutOrderRepository;
import kitchenpos.takeout_order.domain.TakeoutOrderStatus;
import kitchenpos.takeout_order.domain.TakeoutOrderTable;
import kitchenpos.takeout_order.domain.TakeoutOrderTableRepository;
import kitchenpos.takeout_order.domain.TakeoutOrderType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TakeoutOrderService {
    private final TakeoutOrderRepository takeoutOrderRepository;
    private final MenuRepository menuRepository;
    private final TakeoutOrderTableRepository takeoutOrderTableRepository;
    private final KitchenridersClient kitchenridersClient;

    public TakeoutOrderService(
        final TakeoutOrderRepository takeoutOrderRepository,
        final MenuRepository menuRepository,
        final TakeoutOrderTableRepository takeoutOrderTableRepository,
        final KitchenridersClient kitchenridersClient
    ) {
        this.takeoutOrderRepository = takeoutOrderRepository;
        this.menuRepository = menuRepository;
        this.takeoutOrderTableRepository = takeoutOrderTableRepository;
        this.kitchenridersClient = kitchenridersClient;
    }

    @Transactional
    public TakeoutOrder create(final TakeoutOrder request) {
        final TakeoutOrderType type = request.getType();
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException();
        }
        final List<TakeoutOrderLineItem> takeoutOrderLineItemRequests = request.getOrderLineItems();
        if (Objects.isNull(takeoutOrderLineItemRequests) || takeoutOrderLineItemRequests.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final List<Menu> menus = menuRepository.findAllByIdIn(
            takeoutOrderLineItemRequests.stream()
                .map(TakeoutOrderLineItem::getMenuId)
                .collect(Collectors.toList())
        );
        if (menus.size() != takeoutOrderLineItemRequests.size()) {
            throw new IllegalArgumentException();
        }
        final List<TakeoutOrderLineItem> takeoutOrderLineItems = new ArrayList<>();
        for (final TakeoutOrderLineItem takeoutOrderLineItemRequest : takeoutOrderLineItemRequests) {
            final long quantity = takeoutOrderLineItemRequest.getQuantity();
            if (type != TakeoutOrderType.EAT_IN) {
                if (quantity < 0) {
                    throw new IllegalArgumentException();
                }
            }
            final Menu menu = menuRepository.findById(takeoutOrderLineItemRequest.getMenuId())
                .orElseThrow(NoSuchElementException::new);
            if (!menu.isDisplayed()) {
                throw new IllegalStateException();
            }
            if (menu.getPrice().compareTo(takeoutOrderLineItemRequest.getPrice()) != 0) {
                throw new IllegalArgumentException();
            }
            final TakeoutOrderLineItem takeoutOrderLineItem = new TakeoutOrderLineItem();
            takeoutOrderLineItem.setMenu(menu);
            takeoutOrderLineItem.setQuantity(quantity);
            takeoutOrderLineItems.add(takeoutOrderLineItem);
        }
        TakeoutOrder takeoutOrder = new TakeoutOrder();
        takeoutOrder.setId(UUID.randomUUID());
        takeoutOrder.setType(type);
        takeoutOrder.setStatus(TakeoutOrderStatus.WAITING);
        takeoutOrder.setOrderDateTime(LocalDateTime.now());
        takeoutOrder.setOrderLineItems(takeoutOrderLineItems);
        if (type == TakeoutOrderType.DELIVERY) {
            final String deliveryAddress = request.getDeliveryAddress();
            if (Objects.isNull(deliveryAddress) || deliveryAddress.isEmpty()) {
                throw new IllegalArgumentException();
            }
            takeoutOrder.setDeliveryAddress(deliveryAddress);
        }
        if (type == TakeoutOrderType.EAT_IN) {
            final TakeoutOrderTable takeoutOrderTable = takeoutOrderTableRepository.findById(request.getOrderTableId())
                .orElseThrow(NoSuchElementException::new);
            if (!takeoutOrderTable.isOccupied()) {
                throw new IllegalStateException();
            }
            takeoutOrder.setOrderTable(takeoutOrderTable);
        }
        return takeoutOrderRepository.save(takeoutOrder);
    }

    @Transactional
    public TakeoutOrder accept(final UUID orderId) {
        final TakeoutOrder takeoutOrder = takeoutOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (takeoutOrder.getStatus() != TakeoutOrderStatus.WAITING) {
            throw new IllegalStateException();
        }
        if (takeoutOrder.getType() == TakeoutOrderType.DELIVERY) {
            BigDecimal sum = BigDecimal.ZERO;
            for (final TakeoutOrderLineItem takeoutOrderLineItem : takeoutOrder.getOrderLineItems()) {
                sum = takeoutOrderLineItem.getMenu()
                    .getPrice()
                    .multiply(BigDecimal.valueOf(takeoutOrderLineItem.getQuantity()));
            }
            kitchenridersClient.requestDelivery(orderId, sum, takeoutOrder.getDeliveryAddress());
        }
        takeoutOrder.setStatus(TakeoutOrderStatus.ACCEPTED);
        return takeoutOrder;
    }

    @Transactional
    public TakeoutOrder serve(final UUID orderId) {
        final TakeoutOrder takeoutOrder = takeoutOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (takeoutOrder.getStatus() != TakeoutOrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        takeoutOrder.setStatus(TakeoutOrderStatus.SERVED);
        return takeoutOrder;
    }

    @Transactional
    public TakeoutOrder startDelivery(final UUID orderId) {
        final TakeoutOrder takeoutOrder = takeoutOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (takeoutOrder.getType() != TakeoutOrderType.DELIVERY) {
            throw new IllegalStateException();
        }
        if (takeoutOrder.getStatus() != TakeoutOrderStatus.SERVED) {
            throw new IllegalStateException();
        }
        takeoutOrder.setStatus(TakeoutOrderStatus.DELIVERING);
        return takeoutOrder;
    }

    @Transactional
    public TakeoutOrder completeDelivery(final UUID orderId) {
        final TakeoutOrder takeoutOrder = takeoutOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (takeoutOrder.getStatus() != TakeoutOrderStatus.DELIVERING) {
            throw new IllegalStateException();
        }
        takeoutOrder.setStatus(TakeoutOrderStatus.DELIVERED);
        return takeoutOrder;
    }

    @Transactional
    public TakeoutOrder complete(final UUID orderId) {
        final TakeoutOrder takeoutOrder = takeoutOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        final TakeoutOrderType type = takeoutOrder.getType();
        final TakeoutOrderStatus status = takeoutOrder.getStatus();
        if (type == TakeoutOrderType.DELIVERY) {
            if (status != TakeoutOrderStatus.DELIVERED) {
                throw new IllegalStateException();
            }
        }
        if (type == TakeoutOrderType.TAKEOUT || type == TakeoutOrderType.EAT_IN) {
            if (status != TakeoutOrderStatus.SERVED) {
                throw new IllegalStateException();
            }
        }
        takeoutOrder.setStatus(TakeoutOrderStatus.COMPLETED);
        if (type == TakeoutOrderType.EAT_IN) {
            final TakeoutOrderTable takeoutOrderTable = takeoutOrder.getOrderTable();
            if (!takeoutOrderRepository.existsByOrderTableAndStatusNot(takeoutOrderTable, TakeoutOrderStatus.COMPLETED)) {
                takeoutOrderTable.setNumberOfGuests(0);
                takeoutOrderTable.setOccupied(false);
            }
        }
        return takeoutOrder;
    }

    @Transactional(readOnly = true)
    public List<TakeoutOrder> findAll() {
        return takeoutOrderRepository.findAll();
    }
}
