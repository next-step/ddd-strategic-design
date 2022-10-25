package kitchenpos.eatinorder.repository;

import kitchenpos.eatinorder.domain.EatInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaEatInOrderRepository extends EatInOrderRepository, JpaRepository<EatInOrder, UUID> {
}
