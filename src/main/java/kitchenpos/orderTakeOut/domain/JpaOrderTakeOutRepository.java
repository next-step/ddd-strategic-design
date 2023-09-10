package kitchenpos.orderTakeOut.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderTakeOutRepository extends OrderTakeOutRepository, JpaRepository<OrderTakeOut, UUID> {
}
