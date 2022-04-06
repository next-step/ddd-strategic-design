package kitchenpos.order.application.port.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kitchenpos.order.domain.Order;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.ordertable.domain.OrderTable;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findById(UUID id);

    List<Order> findAll();

    boolean existsByOrderTableAndStatusNot(OrderTable orderTable, OrderStatus status);
}

