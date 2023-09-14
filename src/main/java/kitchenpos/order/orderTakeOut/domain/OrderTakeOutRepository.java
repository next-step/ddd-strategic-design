package kitchenpos.order.orderTakeOut.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderTakeOutRepository {
    OrderTakeOut save(OrderTakeOut order);

    Optional<OrderTakeOut> findById(UUID id);

    List<OrderTakeOut> findAll();

}
