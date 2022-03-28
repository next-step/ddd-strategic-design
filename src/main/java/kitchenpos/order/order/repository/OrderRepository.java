package kitchenpos.order.order.repository;

import kitchenpos.order.order.domain.Order;
import kitchenpos.order.order.domain.OrderStatus;
import kitchenpos.order.ordertable.domain.OrderTable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findById(UUID id);

    List<Order> findAll();

    boolean existsByOrderTableAndStatusNot(OrderTable orderTable, OrderStatus status);
}

