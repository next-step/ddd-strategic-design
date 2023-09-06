package kitchenpos.order.infra.common;

import kitchenpos.order.domain.common.Order;
import kitchenpos.order.domain.enums.OrderStatus;
import kitchenpos.order.domain.OrderTable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findById(UUID id);

    List<Order> findAll();

    boolean existsByOrderTableAndStatusNot(OrderTable orderTable, OrderStatus status);
}

