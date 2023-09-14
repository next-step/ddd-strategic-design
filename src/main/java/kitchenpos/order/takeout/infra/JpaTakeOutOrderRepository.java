package kitchenpos.order.takeout.infra;

import kitchenpos.order.takeout.domain.TakeOutOrder;
import kitchenpos.order.takeout.domain.TakeOutOrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaTakeOutOrderRepository extends TakeOutOrderRepository, JpaRepository<TakeOutOrder, UUID> {
}
