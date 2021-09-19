package tobe.kitchenpos.order.domain.takeout;

import org.springframework.data.jpa.repository.JpaRepository;
import tobe.kitchenpos.order.domain.common.Order;

import java.util.UUID;

public interface JpaTakeOutOrderRepository extends TakeOutOrderRepository, JpaRepository<Order, UUID> {
}
