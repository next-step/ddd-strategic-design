package kitchenpos.order.deliveryorder.domain.repository;

import kitchenpos.order.common.domain.OrderStatus;
import kitchenpos.order.common.domain.model.Order;
import kitchenpos.order.common.domain.repository.OrderRepository;
import kitchenpos.order.eatinorder.domain.model.OrderTable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryOrderRepository extends OrderRepository {
    Order save(Order order);

    Optional<Order> findById(UUID id);

    List<Order> findAll();

}

