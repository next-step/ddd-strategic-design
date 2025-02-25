package kitchenpos.deliveryorders.application;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;
import kitchenpos.deliveryorders.adapter.out.client.KitchenridersClient;
import kitchenpos.deliveryorders.application.out.DeliveryOrderRepository;
import kitchenpos.deliveryorders.domain.DeliveryOrder;
import kitchenpos.deliveryorders.domain.DeliveryOrderLineItem;
import kitchenpos.deliveryorders.domain.DeliveryOrderStatus;
import kitchenpos.menus.application.out.MenuRepository;
import kitchenpos.menus.domain.Menu;
import kitchenpos.shared.domain.OrderType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliveryOrderService {

    private final DeliveryOrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final KitchenridersClient kitchenridersClient;

    public DeliveryOrderService(
        final DeliveryOrderRepository orderRepository,
        final MenuRepository menuRepository,
        final KitchenridersClient kitchenridersClient
    ) {
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
        this.kitchenridersClient = kitchenridersClient;
    }

    @Transactional
    public DeliveryOrder create(final DeliveryOrder request) {
        final OrderType type = request.getType();
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException();
        }
        final List<DeliveryOrderLineItem> orderLineItemRequests = request.getOrderLineItems();
        if (Objects.isNull(orderLineItemRequests) || orderLineItemRequests.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final List<Menu> menus = menuRepository.findAllByIdIn(
            orderLineItemRequests.stream()
                .map(DeliveryOrderLineItem::getMenuId)
                .toList()
        );
        if (menus.size() != orderLineItemRequests.size()) {
            throw new IllegalArgumentException();
        }
        final List<DeliveryOrderLineItem> orderLineItems = new ArrayList<>();
        for (final DeliveryOrderLineItem orderLineItemRequest : orderLineItemRequests) {
            final long quantity = orderLineItemRequest.getQuantity();
            if (type != OrderType.EAT_IN) {
                if (quantity < 0) {
                    throw new IllegalArgumentException();
                }
            }
            final Menu menu = menuRepository.findById(orderLineItemRequest.getMenuId())
                .orElseThrow(NoSuchElementException::new);
            if (!menu.isDisplayed()) {
                throw new IllegalStateException();
            }
            if (menu.getPrice().compareTo(orderLineItemRequest.getPrice()) != 0) {
                throw new IllegalArgumentException();
            }
            final DeliveryOrderLineItem orderLineItem = new DeliveryOrderLineItem();
            orderLineItem.setMenu(menu);
            orderLineItem.setQuantity(quantity);
            orderLineItems.add(orderLineItem);
        }
        DeliveryOrder order = new DeliveryOrder();
        order.setId(UUID.randomUUID());
        order.setType(type);
        order.setStatus(DeliveryOrderStatus.WAITING);
        order.setOrderDateTime(LocalDateTime.now());
        order.setOrderLineItems(orderLineItems);
        if (type == OrderType.DELIVERY) {
            final String deliveryAddress = request.getDeliveryAddress();
            if (Objects.isNull(deliveryAddress) || deliveryAddress.isEmpty()) {
                throw new IllegalArgumentException();
            }
            order.setDeliveryAddress(deliveryAddress);
        }
        return orderRepository.save(order);
    }

    @Transactional
    public DeliveryOrder accept(final UUID orderId) {
        final DeliveryOrder order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != DeliveryOrderStatus.WAITING) {
            throw new IllegalStateException();
        }
        if (order.getType() == OrderType.DELIVERY) {
            BigDecimal sum = BigDecimal.ZERO;
            for (final DeliveryOrderLineItem orderLineItem : order.getOrderLineItems()) {
                sum = orderLineItem.getMenu()
                    .getPrice()
                    .multiply(BigDecimal.valueOf(orderLineItem.getQuantity()));
            }
            kitchenridersClient.requestDelivery(orderId, sum, order.getDeliveryAddress());
        }
        order.setStatus(DeliveryOrderStatus.ACCEPTED);
        return order;
    }

    @Transactional
    public DeliveryOrder serve(final UUID orderId) {
        final DeliveryOrder order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != DeliveryOrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        order.setStatus(DeliveryOrderStatus.SERVED);
        return order;
    }

    @Transactional
    public DeliveryOrder startDelivery(final UUID orderId) {
        final DeliveryOrder order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getType() != OrderType.DELIVERY) {
            throw new IllegalStateException();
        }
        if (order.getStatus() != DeliveryOrderStatus.SERVED) {
            throw new IllegalStateException();
        }
        order.setStatus(DeliveryOrderStatus.DELIVERING);
        return order;
    }

    @Transactional
    public DeliveryOrder completeDelivery(final UUID orderId) {
        final DeliveryOrder order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != DeliveryOrderStatus.DELIVERING) {
            throw new IllegalStateException();
        }
        order.setStatus(DeliveryOrderStatus.DELIVERED);
        return order;
    }

    @Transactional
    public DeliveryOrder complete(final UUID orderId) {
        final DeliveryOrder order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        final OrderType type = order.getType();
        final DeliveryOrderStatus status = order.getStatus();
        if (type == OrderType.DELIVERY) {
            if (status != DeliveryOrderStatus.DELIVERED) {
                throw new IllegalStateException();
            }
        }
        if (type == OrderType.TAKEOUT || type == OrderType.EAT_IN) {
            if (status != DeliveryOrderStatus.SERVED) {
                throw new IllegalStateException();
            }
        }
        order.setStatus(DeliveryOrderStatus.COMPLETED);
        return order;
    }

    @Transactional(readOnly = true)
    public List<DeliveryOrder> findAll() {
        return orderRepository.findAll();
    }
}
