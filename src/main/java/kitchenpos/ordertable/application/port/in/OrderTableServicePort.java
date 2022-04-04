package kitchenpos.ordertable.application.port.in;

import java.util.List;
import java.util.UUID;

import kitchenpos.ordertable.domain.OrderTable;

public interface OrderTableServicePort {
    OrderTable create(OrderTable request);

    OrderTable sit(UUID orderTableId);

    OrderTable clear(UUID orderTableId);

    OrderTable changeNumberOfGuests(UUID orderTableId, OrderTable request);

    List<OrderTable> findAll();
}
