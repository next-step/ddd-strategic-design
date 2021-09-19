package tobe.kitchenpos.order.domain.takeout;

import tobe.kitchenpos.order.domain.common.Order;
import tobe.kitchenpos.order.domain.common.OrderStatus;
import tobe.kitchenpos.order.domain.common.OrderTable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TakeOutOrderRepository {
    Order save(Order order);

    Optional<Order> findById(UUID id);

    List<Order> findAll();

    boolean existsByOrderTableAndStatusNot(OrderTable orderTable, OrderStatus status);
}

