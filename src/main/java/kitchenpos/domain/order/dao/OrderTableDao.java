package kitchenpos.domain.order.dao;

import kitchenpos.domain.order.model.OrderTable;

import java.util.List;
import java.util.Optional;

public interface OrderTableDao {
    OrderTable save(OrderTable entity);

    Optional<OrderTable> findById(Long id);

    List<OrderTable> findAll();

    List<OrderTable> findAllByIdIn(List<Long> ids);

    List<OrderTable> findAllByTableGroupId(Long tableGroupId);
}
