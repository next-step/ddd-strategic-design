package kitchenpos.eatinorder.application.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import kitchenpos.eatinorder.domain.OrderTable;

public interface OrderTableRepository {
    OrderTable save(OrderTable orderTable);

    Optional<OrderTable> findById(UUID id);

    List<OrderTable> findAll();
}

