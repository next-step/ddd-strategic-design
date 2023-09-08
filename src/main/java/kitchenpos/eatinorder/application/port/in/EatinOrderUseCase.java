package kitchenpos.eatinorder.application.port.in;

import java.util.List;
import java.util.UUID;

import kitchenpos.eatinorder.domain.EatinOrder;

public interface EatinOrderUseCase {
	EatinOrder create(final EatinOrder request);

	EatinOrder accept(final UUID orderId);

	EatinOrder serve(final UUID orderId);

	List<EatinOrder> findAll();
}
