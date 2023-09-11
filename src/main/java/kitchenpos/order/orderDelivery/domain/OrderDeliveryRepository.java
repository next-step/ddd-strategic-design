package kitchenpos.order.orderDelivery.domain;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderDeliveryRepository {
    OrderDelivery save(OrderDelivery order);

    Optional<OrderDelivery> findById(UUID id);

    List<OrderDelivery> findAll();
}
