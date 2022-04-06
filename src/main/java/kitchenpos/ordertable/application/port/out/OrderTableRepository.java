package kitchenpos.ordertable.application.port.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kitchenpos.ordertable.domain.OrderTable;

public interface OrderTableRepository {
    OrderTable save(OrderTable orderTable);

    Optional<OrderTable> findById(UUID id);

    List<OrderTable> findAll();
}

