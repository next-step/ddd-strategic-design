package tobe.kitchenpos.order.delivery.domain;

import tobe.kitchenpos.order.common.domain.Order;
import tobe.kitchenpos.order.common.domain.OrderType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryOrderRepository {
    Order save(Order order);

    Optional<Order> findById(UUID id);

    List<Order> findAll();

    List<Order> findByOrderType(OrderType orderType);
}

