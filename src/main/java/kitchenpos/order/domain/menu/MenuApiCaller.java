package kitchenpos.order.domain.menu;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuApiCaller {
    Optional<Menu> findById(UUID menuId);
    List<Menu> findAllByIdIn(List<UUID> ids);
}
