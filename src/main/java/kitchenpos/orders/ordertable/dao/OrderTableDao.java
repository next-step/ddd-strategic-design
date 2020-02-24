package kitchenpos.orders.ordertable.dao;

import java.util.List;
import java.util.Optional;
import kitchenpos.orders.ordertable.domain.OrderTable;

public interface OrderTableDao {

    OrderTable save(OrderTable entity);

    Optional<OrderTable> findById(Long id);

    List<OrderTable> findAll();

    List<OrderTable> findAllByIdIn(List<Long> ids);

    List<OrderTable> findAllByTableGroupId(Long tableGroupId);
}
