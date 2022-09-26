package kitchenpos.takeoutorder.domain;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTakeoutOrderRepository extends
    TakeoutOrderRepository, JpaRepository<TakeoutOrder, UUID> {
}
