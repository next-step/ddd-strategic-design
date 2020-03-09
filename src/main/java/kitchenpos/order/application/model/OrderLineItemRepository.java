package kitchenpos.order.application.model;

import java.util.List;
import java.util.Optional;

public interface OrderLineItemRepository {
    OrderLineItem save(OrderLineItem entity);

    Optional<OrderLineItem> findById(Long id);

    List<OrderLineItem> findAll();

    List<OrderLineItem> findAllByOrderId(Long orderId);
}
