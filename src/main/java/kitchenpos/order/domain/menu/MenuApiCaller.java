package kitchenpos.order.domain.menu;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuApiCaller {
    Optional<OrderMenu> findById(UUID menuId);
    List<OrderMenu> findAllByIdIn(List<UUID> ids);
}
