package kitchenpos.takeoutorders.adapter.out.persistence;

import java.util.UUID;
import kitchenpos.takeoutorders.application.out.TakeOutOrderRepository;
import kitchenpos.takeoutorders.domain.TakeOutOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTakeOutOrderRepository extends TakeOutOrderRepository,
    JpaRepository<TakeOutOrder, UUID> {

}
