package kitchenpos.eatinorder.infrastructure;

import kitchenpos.eatinorder.domain.RestaurantTable;
import kitchenpos.eatinorder.domain.RestaurantTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaRestaurantTableRepository extends RestaurantTableRepository, JpaRepository<RestaurantTable, UUID> {
}
