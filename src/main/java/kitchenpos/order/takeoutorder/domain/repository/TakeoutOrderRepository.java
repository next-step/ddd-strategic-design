package kitchenpos.order.takeoutorder.domain.repository;

import kitchenpos.order.common.domain.model.Order;
import kitchenpos.order.common.domain.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TakeoutOrderRepository extends OrderRepository {
    Order save(Order order);

    Optional<Order> findById(UUID id);

    List<Order> findAll();

}

