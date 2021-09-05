package kitchenpos.ordertable.application.usecase;

import kitchenpos.ordertable.domain.OrderTable;

import java.util.UUID;

public interface ManipulateOrderTable {
    OrderTable sit(final UUID orderTableId);

    OrderTable clear(final UUID orderTableId);

    OrderTable changeNumberOfGuests(final UUID orderTableId, final OrderTable request);
}
