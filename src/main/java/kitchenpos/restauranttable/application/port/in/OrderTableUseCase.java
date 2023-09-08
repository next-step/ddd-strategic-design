package kitchenpos.restauranttable.application.port.in;

import java.util.List;
import java.util.UUID;

import kitchenpos.restauranttable.domain.OrderTable;

public interface OrderTableUseCase {
	OrderTable create(final OrderTable request);

	OrderTable sit(final UUID orderTableId);

	OrderTable clear(final UUID orderTableId);

	OrderTable changeNumberOfGuests(final UUID orderTableId, final OrderTable request);

	List<OrderTable> findAll();
}
