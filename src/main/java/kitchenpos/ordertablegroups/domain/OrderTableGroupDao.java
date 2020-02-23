package kitchenpos.ordertablegroups.domain;

import java.util.List;
import java.util.Optional;

public interface OrderTableGroupDao {
    OrderTableGroup save(OrderTableGroup entity);

    Optional<OrderTableGroup> findById(Long id);

    List<OrderTableGroup> findAll();
}
