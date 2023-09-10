package kitchenpos.order.takeoutorder.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaTakeOutRepository extends TakeOutOrderRepository, JpaRepository<TakeOutOrder, UUID> {
}
