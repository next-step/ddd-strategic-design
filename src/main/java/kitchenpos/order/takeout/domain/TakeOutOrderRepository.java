package kitchenpos.order.takeout.domain;

import kitchenpos.order.deliveryorder.domain.DeliveryOrder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TakeOutOrderRepository {
    TakeOutOrder save(TakeOutOrder deliveryOrder);

    Optional<TakeOutOrder> findById(UUID id);

    List<TakeOutOrder> findAll();

}

