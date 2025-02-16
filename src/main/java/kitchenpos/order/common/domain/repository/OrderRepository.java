package kitchenpos.order.common.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import kitchenpos.order.common.domain.model.Order;
import kitchenpos.order.common.domain.model.OrderStatus;
import kitchenpos.order.eatin.domain.model.OrderTable;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findById(UUID id);

    List<Order> findAll();

    boolean existsByOrderTableAndStatusNot(OrderTable orderTable, OrderStatus status);
}
