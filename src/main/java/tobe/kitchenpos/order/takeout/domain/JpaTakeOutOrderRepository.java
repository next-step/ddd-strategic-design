package tobe.kitchenpos.order.takeout.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import tobe.kitchenpos.order.common.domain.Order;

import java.util.UUID;

public interface JpaTakeOutOrderRepository extends TakeOutOrderRepository, JpaRepository<Order, UUID> {
}
