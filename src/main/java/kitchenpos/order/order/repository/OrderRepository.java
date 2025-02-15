package kitchenpos.order.order.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import kitchenpos.order.order.model.Order;
import kitchenpos.order.order.model.OrderStatus;
import kitchenpos.order.eatin.model.OrderTable;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findById(UUID id);

    List<Order> findAll();

    boolean existsByOrderTableAndStatusNot(OrderTable orderTable, OrderStatus status);
}

