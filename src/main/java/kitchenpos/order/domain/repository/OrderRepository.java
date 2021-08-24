package kitchenpos.order.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import kitchenpos.ordertable.domain.model.OrderTable;
import kitchenpos.order.domain.model.Order;
import kitchenpos.order.domain.model.OrderStatus;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findById(UUID id);

    List<Order> findAll();

    boolean existsByOrderTableAndStatusNot(OrderTable orderTable, OrderStatus status);
}

