package kitchenpos.common.infra;

import kitchenpos.eatin.domain.Order;
import kitchenpos.eatin.domain.OrderStatus;
import kitchenpos.eatin.domain.OrderTable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findById(UUID id);

    List<Order> findAll();

    boolean existsByOrderTableAndStatusNot(OrderTable orderTable, OrderStatus status);
}

