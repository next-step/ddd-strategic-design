package kitchenpos.delivery_order.application;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import kitchenpos.delivery_order.domain.DeliveryOrder;
import kitchenpos.delivery_order.domain.DeliveryOrderLineItem;
import kitchenpos.delivery_order.domain.DeliveryOrderRepository;
import kitchenpos.delivery_order.domain.DeliveryOrderStatus;
import kitchenpos.delivery_order.domain.DeliveryOrderTable;
import kitchenpos.delivery_order.domain.DeliveryOrderTableRepository;
import kitchenpos.delivery_order.domain.DeliveryOrderType;
import kitchenpos.delivery_order.infra.KitchenridersClient;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliveryOrderService {
    private final DeliveryOrderRepository deliveryOrderRepository;
    private final MenuRepository menuRepository;
    private final DeliveryOrderTableRepository deliveryOrderTableRepository;
    private final KitchenridersClient kitchenridersClient;

    public DeliveryOrderService(
        final DeliveryOrderRepository deliveryOrderRepository,
        final MenuRepository menuRepository,
        final DeliveryOrderTableRepository deliveryOrderTableRepository,
        final KitchenridersClient kitchenridersClient
    ) {
        this.deliveryOrderRepository = deliveryOrderRepository;
        this.menuRepository = menuRepository;
        this.deliveryOrderTableRepository = deliveryOrderTableRepository;
        this.kitchenridersClient = kitchenridersClient;
    }

    @Transactional
    public DeliveryOrder create(final DeliveryOrder request) {
        final DeliveryOrderType type = request.getType();
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException();
        }
        final List<DeliveryOrderLineItem> deliveryOrderLineItemRequests = request.getOrderLineItems();
        if (Objects.isNull(deliveryOrderLineItemRequests) || deliveryOrderLineItemRequests.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final List<Menu> menus = menuRepository.findAllByIdIn(
            deliveryOrderLineItemRequests.stream()
                .map(DeliveryOrderLineItem::getMenuId)
                .collect(Collectors.toList())
        );
        if (menus.size() != deliveryOrderLineItemRequests.size()) {
            throw new IllegalArgumentException();
        }
        final List<DeliveryOrderLineItem> deliveryOrderLineItems = new ArrayList<>();
        for (final DeliveryOrderLineItem deliveryOrderLineItemRequest : deliveryOrderLineItemRequests) {
            final long quantity = deliveryOrderLineItemRequest.getQuantity();
            if (type != DeliveryOrderType.EAT_IN) {
                if (quantity < 0) {
                    throw new IllegalArgumentException();
                }
            }
            final Menu menu = menuRepository.findById(deliveryOrderLineItemRequest.getMenuId())
                .orElseThrow(NoSuchElementException::new);
            if (!menu.isDisplayed()) {
                throw new IllegalStateException();
            }
            if (menu.getPrice().compareTo(deliveryOrderLineItemRequest.getPrice()) != 0) {
                throw new IllegalArgumentException();
            }
            final DeliveryOrderLineItem deliveryOrderLineItem = new DeliveryOrderLineItem();
            deliveryOrderLineItem.setMenu(menu);
            deliveryOrderLineItem.setQuantity(quantity);
            deliveryOrderLineItems.add(deliveryOrderLineItem);
        }
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setId(UUID.randomUUID());
        deliveryOrder.setType(type);
        deliveryOrder.setStatus(DeliveryOrderStatus.WAITING);
        deliveryOrder.setOrderDateTime(LocalDateTime.now());
        deliveryOrder.setOrderLineItems(deliveryOrderLineItems);
        if (type == DeliveryOrderType.DELIVERY) {
            final String deliveryAddress = request.getDeliveryAddress();
            if (Objects.isNull(deliveryAddress) || deliveryAddress.isEmpty()) {
                throw new IllegalArgumentException();
            }
            deliveryOrder.setDeliveryAddress(deliveryAddress);
        }
        if (type == DeliveryOrderType.EAT_IN) {
            final DeliveryOrderTable deliveryOrderTable = deliveryOrderTableRepository.findById(request.getOrderTableId())
                .orElseThrow(NoSuchElementException::new);
            if (!deliveryOrderTable.isOccupied()) {
                throw new IllegalStateException();
            }
            deliveryOrder.setOrderTable(deliveryOrderTable);
        }
        return deliveryOrderRepository.save(deliveryOrder);
    }

    @Transactional
    public DeliveryOrder accept(final UUID orderId) {
        final DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (deliveryOrder.getStatus() != DeliveryOrderStatus.WAITING) {
            throw new IllegalStateException();
        }
        if (deliveryOrder.getType() == DeliveryOrderType.DELIVERY) {
            BigDecimal sum = BigDecimal.ZERO;
            for (final DeliveryOrderLineItem deliveryOrderLineItem : deliveryOrder.getOrderLineItems()) {
                sum = deliveryOrderLineItem.getMenu()
                    .getPrice()
                    .multiply(BigDecimal.valueOf(deliveryOrderLineItem.getQuantity()));
            }
            kitchenridersClient.requestDelivery(orderId, sum, deliveryOrder.getDeliveryAddress());
        }
        deliveryOrder.setStatus(DeliveryOrderStatus.ACCEPTED);
        return deliveryOrder;
    }

    @Transactional
    public DeliveryOrder serve(final UUID orderId) {
        final DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (deliveryOrder.getStatus() != DeliveryOrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        deliveryOrder.setStatus(DeliveryOrderStatus.SERVED);
        return deliveryOrder;
    }

    @Transactional
    public DeliveryOrder startDelivery(final UUID orderId) {
        final DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (deliveryOrder.getType() != DeliveryOrderType.DELIVERY) {
            throw new IllegalStateException();
        }
        if (deliveryOrder.getStatus() != DeliveryOrderStatus.SERVED) {
            throw new IllegalStateException();
        }
        deliveryOrder.setStatus(DeliveryOrderStatus.DELIVERING);
        return deliveryOrder;
    }

    @Transactional
    public DeliveryOrder completeDelivery(final UUID orderId) {
        final DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (deliveryOrder.getStatus() != DeliveryOrderStatus.DELIVERING) {
            throw new IllegalStateException();
        }
        deliveryOrder.setStatus(DeliveryOrderStatus.DELIVERED);
        return deliveryOrder;
    }

    @Transactional
    public DeliveryOrder complete(final UUID orderId) {
        final DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        final DeliveryOrderType type = deliveryOrder.getType();
        final DeliveryOrderStatus status = deliveryOrder.getStatus();
        if (type == DeliveryOrderType.DELIVERY) {
            if (status != DeliveryOrderStatus.DELIVERED) {
                throw new IllegalStateException();
            }
        }
        if (type == DeliveryOrderType.TAKEOUT || type == DeliveryOrderType.EAT_IN) {
            if (status != DeliveryOrderStatus.SERVED) {
                throw new IllegalStateException();
            }
        }
        deliveryOrder.setStatus(DeliveryOrderStatus.COMPLETED);
        if (type == DeliveryOrderType.EAT_IN) {
            final DeliveryOrderTable deliveryOrderTable = deliveryOrder.getOrderTable();
            if (!deliveryOrderRepository.existsByOrderTableAndStatusNot(deliveryOrderTable, DeliveryOrderStatus.COMPLETED)) {
                deliveryOrderTable.setNumberOfGuests(0);
                deliveryOrderTable.setOccupied(false);
            }
        }
        return deliveryOrder;
    }

    @Transactional(readOnly = true)
    public List<DeliveryOrder> findAll() {
        return deliveryOrderRepository.findAll();
    }
}
