package kitchenpos.deliveryorder.application;

import kitchenpos.deliveryorder.domain.DeliveryOrder;
import kitchenpos.deliveryorder.domain.DeliveryOrderLineItem;
import kitchenpos.deliveryorder.domain.OrderStatus;
import kitchenpos.deliveryorder.domain.OrderType;
import kitchenpos.deliveryorder.infra.KitchenridersClient;
import kitchenpos.deliveryorder.repository.DeliveryOrderRepository;
import kitchenpos.menu.menu.domain.Menu;
import kitchenpos.menu.menu.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeliverOrderService {
    private final DeliveryOrderRepository deliveryOrderRepository;
    private final MenuRepository menuRepository;
    private final KitchenridersClient kitchenridersClient;

    public DeliverOrderService(
            final DeliveryOrderRepository deliveryOrderRepository,
            final MenuRepository menuRepository,
            final KitchenridersClient kitchenridersClient
    ) {
        this.deliveryOrderRepository = deliveryOrderRepository;
        this.menuRepository = menuRepository;
        this.kitchenridersClient = kitchenridersClient;
    }

    @Transactional
    public DeliveryOrder create(final DeliveryOrder request) {
        final OrderType type = request.getType();
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
        deliveryOrder.setStatus(OrderStatus.WAITING);
        deliveryOrder.setOrderDateTime(LocalDateTime.now());
        deliveryOrder.setOrderLineItems(deliveryOrderLineItems);
        final String deliveryAddress = request.getDeliveryAddress();
        if (Objects.isNull(deliveryAddress) || deliveryAddress.isEmpty()) {
            throw new IllegalArgumentException();
        }
        deliveryOrder.setDeliveryAddress(deliveryAddress);
        return deliveryOrderRepository.save(deliveryOrder);
    }

    @Transactional
    public DeliveryOrder accept(final UUID orderId) {
        final DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);
        if (deliveryOrder.getStatus() != OrderStatus.WAITING) {
            throw new IllegalStateException();
        }
        if (deliveryOrder.getType() == OrderType.DELIVERY) {
            BigDecimal sum = BigDecimal.ZERO;
            for (final DeliveryOrderLineItem deliveryOrderLineItem : deliveryOrder.getOrderLineItems()) {
                sum = deliveryOrderLineItem.getMenu()
                        .getPrice()
                        .multiply(BigDecimal.valueOf(deliveryOrderLineItem.getQuantity()));
            }
            kitchenridersClient.requestDelivery(orderId, sum, deliveryOrder.getDeliveryAddress());
        }
        deliveryOrder.setStatus(OrderStatus.ACCEPTED);
        return deliveryOrder;
    }

    @Transactional
    public DeliveryOrder serve(final UUID orderId) {
        final DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);
        if (deliveryOrder.getStatus() != OrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        deliveryOrder.setStatus(OrderStatus.SERVED);
        return deliveryOrder;
    }

    @Transactional
    public DeliveryOrder startDelivery(final UUID orderId) {
        final DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);
        if (deliveryOrder.getType() != OrderType.DELIVERY) {
            throw new IllegalStateException();
        }
        if (deliveryOrder.getStatus() != OrderStatus.SERVED) {
            throw new IllegalStateException();
        }
        deliveryOrder.setStatus(OrderStatus.DELIVERING);
        return deliveryOrder;
    }

    @Transactional
    public DeliveryOrder completeDelivery(final UUID orderId) {
        final DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);
        if (deliveryOrder.getStatus() != OrderStatus.DELIVERING) {
            throw new IllegalStateException();
        }
        deliveryOrder.setStatus(OrderStatus.DELIVERED);
        return deliveryOrder;
    }

    @Transactional
    public DeliveryOrder complete(final UUID orderId) {
        final DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);
        final OrderType type = deliveryOrder.getType();
        final OrderStatus status = deliveryOrder.getStatus();
        if (type == OrderType.DELIVERY) {
            if (status != OrderStatus.DELIVERED) {
                throw new IllegalStateException();
            }
        }
        if (type == OrderType.TAKEOUT || type == OrderType.EAT_IN) {
            if (status != OrderStatus.SERVED) {
                throw new IllegalStateException();
            }
        }
        deliveryOrder.setStatus(OrderStatus.COMPLETED);
        return deliveryOrder;
    }

    @Transactional(readOnly = true)
    public List<DeliveryOrder> findAll() {
        return deliveryOrderRepository.findAll();
    }
}
