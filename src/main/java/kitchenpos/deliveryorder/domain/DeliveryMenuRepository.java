package kitchenpos.deliveryorder.domain;

import kitchenpos.menu.domain.Menu;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryMenuRepository {
    DeliveryMenu save(final DeliveryMenu menu);

    Optional<DeliveryMenu> findById(UUID id);

    List<DeliveryMenu> findAllByIdIn(List<UUID> ids);
}

