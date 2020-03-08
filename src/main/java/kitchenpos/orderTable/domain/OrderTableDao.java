package kitchenpos.orderTable.domain;

import kitchenpos.orderTable.domain.model.OrderTable;

import java.util.List;
import java.util.Optional;

public interface OrderTableDao {
    OrderTable save(OrderTable entity);

    Optional<OrderTable> findById(Long id);

    List<OrderTable> findAll();

    List<OrderTable> findAllByIdIn(List<Long> ids);

    List<OrderTable> findAllByTableGroupId(Long tableGroupId);
}
