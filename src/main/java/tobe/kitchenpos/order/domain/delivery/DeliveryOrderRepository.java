package tobe.kitchenpos.order.domain.delivery;

import tobe.kitchenpos.order.domain.common.Order;
import tobe.kitchenpos.order.domain.common.OrderType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryOrderRepository {
    Order save(Order order);

    Optional<Order> findById(UUID id);

    List<Order> findAll();

    List<Order> findByOrderType(OrderType orderType);
}

