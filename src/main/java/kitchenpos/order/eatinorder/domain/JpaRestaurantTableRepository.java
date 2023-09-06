package kitchenpos.order.eatinorder.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaRestaurantTableRepository extends RestaurantTableRepository, JpaRepository<RestaurantTable, UUID> {
}
