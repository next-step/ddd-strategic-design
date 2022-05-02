package kitchenpos.order.eatin.domain;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEatinOrderRepository extends EatinOrderRepository, JpaRepository<EatinOrder, UUID> {
}
