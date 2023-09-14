package kitchenpos.order.orderEatIn.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderEatInRepository {
    OrderEatIn save(OrderEatIn order);

    Optional<OrderEatIn> findById(UUID id);

    List<OrderEatIn> findAll();

    boolean existsByOrderTableAndStatusNot(OrderTable orderTable, OrderEatInStatus status);
}
