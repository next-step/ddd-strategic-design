package kitchenpos.order.eatin.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kitchenpos.order.eatin.EatinOrderStatus;

public interface EatinOrderRepository {
	EatinOrder save(EatinOrder order);

	Optional<EatinOrder> findById(UUID id);

	List<EatinOrder> findAll();

	boolean existsByOrderTableAndStatusNot(OrderTable orderTable, EatinOrderStatus status);
}
