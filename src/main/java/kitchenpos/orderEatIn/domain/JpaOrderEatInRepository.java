package kitchenpos.orderEatIn.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderEatInRepository extends OrderEatInRepository, JpaRepository<OrderEatIn, UUID> {
}
