package kitchenpos.eatinorder.adapter.out.persistence;

import kitchenpos.eatinorder.domain.EatInOrder;
import kitchenpos.eatinorder.application.out.EatInOrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaEatInOrderRepository extends EatInOrderRepository, JpaRepository<EatInOrder, UUID> {
}
