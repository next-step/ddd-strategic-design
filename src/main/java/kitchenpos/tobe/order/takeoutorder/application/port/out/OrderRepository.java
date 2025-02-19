package kitchenpos.tobe.order.takeoutorder.application.port.out;

import kitchenpos.tobe.order.domain.Order;
import kitchenpos.tobe.order.domain.OrderStatus;
import kitchenpos.tobe.order.eatinorder.domain.OrderTable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findById(UUID id);

    List<Order> findAll();

    boolean existsByOrderTableAndStatusNot(OrderTable orderTable, OrderStatus status);
}

