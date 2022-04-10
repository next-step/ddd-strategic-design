package kitchenpos.order.repository;

import kitchenpos.orderTable.domain.OrderTable;
import kitchenpos.order.domain.Order;
import kitchenpos.order.domain.enums.OrderStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findById(UUID id);

    List<Order> findAll();

    boolean existsByOrderTableAndStatusNot(OrderTable orderTable, OrderStatus status);
}

