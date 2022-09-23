package kitchenpos.order.order.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import kitchenpos.order.order.domain.model.Order;
import kitchenpos.order.order.domain.model.OrderStatus;
import kitchenpos.order.ordertable.domain.model.OrderTable;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findById(UUID id);

    List<Order> findAll();

    boolean existsByOrderTableAndStatusNot(OrderTable orderTable, OrderStatus status);
}

