package kitchenpos.order.eatin.domain;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends EatinOrderRepository, JpaRepository<EatinOrder, UUID> {
}
