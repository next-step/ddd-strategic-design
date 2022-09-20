package kitchenpos.order.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kitchenpos.order.domain.OrderTable;

public interface OrderTableRepository {
    OrderTable save(OrderTable orderTable);

    Optional<OrderTable> findById(UUID id);

    List<OrderTable> findAll();
}

