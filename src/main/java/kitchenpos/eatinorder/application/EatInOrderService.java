package kitchenpos.eatinorder.application;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;
import kitchenpos.eatinorder.domain.EatInOrder;
import kitchenpos.eatinorder.domain.OrderLineItem;
import kitchenpos.eatinorder.application.out.EatInOrderRepository;
import kitchenpos.eatinorder.domain.EatInOrderStatus;
import kitchenpos.eatinorder.domain.OrderTable;
import kitchenpos.eatinorder.application.out.OrderTableRepository;
import kitchenpos.eatinorder.external.MenuClient;
import kitchenpos.eatinorder.external.MenuDTO;
import kitchenpos.shared.domain.OrderType;
import kitchenpos.deliveryorder.adapter.out.client.KitchenridersClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EatInOrderService {
    private final EatInOrderRepository orderRepository;
    private final MenuClient menuClient;
    private final OrderTableRepository orderTableRepository;
    private final KitchenridersClient kitchenridersClient;

    public EatInOrderService(
        final EatInOrderRepository orderRepository,
        final MenuClient menuClient,
        final OrderTableRepository orderTableRepository,
        final KitchenridersClient kitchenridersClient
    ) {
        this.orderRepository = orderRepository;
        this.menuClient = menuClient;
        this.orderTableRepository = orderTableRepository;
        this.kitchenridersClient = kitchenridersClient;
    }

    @Transactional
    public EatInOrder create(final EatInOrder request) {
        final OrderType type = request.getType();
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException("주문 유형이 null입니다.");
        }
        final List<OrderLineItem> orderLineItemRequests = request.getOrderLineItems();
        if (Objects.isNull(orderLineItemRequests) || orderLineItemRequests.isEmpty()) {
            throw new IllegalArgumentException("주문 항목이 비어 있습니다.");
        }

        for (final OrderLineItem orderLineItemRequest : orderLineItemRequests) {
            MenuDTO menuDTO = menuClient.getMenuById(orderLineItemRequest.getMenuId());
            if (menuDTO == null || !menuDTO.isDisplayed()) {
                throw new IllegalStateException("존재하지 않거나 표시되지 않는 메뉴입니다.");
            }
            if (menuDTO.getPrice().compareTo(orderLineItemRequest.getPrice()) != 0) {
                throw new IllegalArgumentException("메뉴 가격이 일치하지 않습니다.");
            }
        }

        EatInOrder order = new EatInOrder();
        order.setId(UUID.randomUUID());
        order.setType(type);
        order.setStatus(EatInOrderStatus.WAITING);
        order.setOrderDateTime(LocalDateTime.now());
        order.setOrderLineItems(orderLineItemRequests);
        
        if (type == OrderType.DELIVERY) {
            final String deliveryAddress = request.getDeliveryAddress();
            if (Objects.isNull(deliveryAddress) || deliveryAddress.isEmpty()) {
                throw new IllegalArgumentException("배달 주소가 유효하지 않습니다.");
            }
            order.setDeliveryAddress(deliveryAddress);
        }
        if (type == OrderType.EAT_IN) {
            final OrderTable orderTable = orderTableRepository.findById(request.getOrderTableId())
                .orElseThrow(NoSuchElementException::new);
            if (!orderTable.isOccupied()) {
                throw new IllegalStateException("테이블이 사용 중이 아닙니다.");
            }
            order.setOrderTable(orderTable);
        }
        return orderRepository.save(order);
    }

    @Transactional
    public EatInOrder accept(final UUID orderId) {
        final EatInOrder order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != EatInOrderStatus.WAITING) {
            throw new IllegalStateException();
        }
        if (order.getType() == OrderType.DELIVERY) {
            BigDecimal sum = BigDecimal.ZERO;
            for (final OrderLineItem orderLineItem : order.getOrderLineItems()) {
                sum = orderLineItem.getMenu()
                    .getPrice()
                    .multiply(BigDecimal.valueOf(orderLineItem.getQuantity()));
            }
            kitchenridersClient.requestDelivery(orderId, sum, order.getDeliveryAddress());
        }
        order.setStatus(EatInOrderStatus.ACCEPTED);
        return order;
    }

    @Transactional
    public EatInOrder serve(final UUID orderId) {
        final EatInOrder order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != EatInOrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        order.setStatus(EatInOrderStatus.SERVED);
        return order;
    }

    @Transactional
    public EatInOrder startDelivery(final UUID orderId) {
        final EatInOrder order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getType() != OrderType.DELIVERY) {
            throw new IllegalStateException();
        }
        if (order.getStatus() != EatInOrderStatus.SERVED) {
            throw new IllegalStateException();
        }
        order.setStatus(EatInOrderStatus.DELIVERING);
        return order;
    }

    @Transactional
    public EatInOrder completeDelivery(final UUID orderId) {
        final EatInOrder order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        if (order.getStatus() != EatInOrderStatus.DELIVERING) {
            throw new IllegalStateException();
        }
        order.setStatus(EatInOrderStatus.DELIVERED);
        return order;
    }

    @Transactional
    public EatInOrder complete(final UUID orderId) {
        final EatInOrder order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        final OrderType type = order.getType();
        final EatInOrderStatus status = order.getStatus();
        if (type == OrderType.DELIVERY) {
            if (status != EatInOrderStatus.DELIVERED) {
                throw new IllegalStateException();
            }
        }
        if (type == OrderType.TAKEOUT || type == OrderType.EAT_IN) {
            if (status != EatInOrderStatus.SERVED) {
                throw new IllegalStateException();
            }
        }
        order.setStatus(EatInOrderStatus.COMPLETED);
        if (type == OrderType.EAT_IN) {
            final OrderTable orderTable = order.getOrderTable();
            if (!orderRepository.existsByOrderTableAndStatusNot(orderTable, EatInOrderStatus.COMPLETED)) {
                orderTable.setNumberOfGuests(0);
                orderTable.setOccupied(false);
            }
        }
        return order;
    }

    @Transactional(readOnly = true)
    public List<EatInOrder> findAll() {
        return orderRepository.findAll();
    }
}
