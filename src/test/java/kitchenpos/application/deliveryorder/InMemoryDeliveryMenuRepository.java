package kitchenpos.application.deliveryorder;

import kitchenpos.deliveryorder.domain.DeliveryMenu;
import kitchenpos.deliveryorder.domain.DeliveryMenuRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryDeliveryMenuRepository implements DeliveryMenuRepository {
    private final Map<UUID, DeliveryMenu> menus = new HashMap<>();


    public DeliveryMenu save(final DeliveryMenu menu) {
        menus.put(menu.getId(), menu);
        return menu;
    }

    @Override
    public Optional<DeliveryMenu> findById(final UUID id) {
        return Optional.ofNullable(menus.get(id));
    }

    @Override
    public List<DeliveryMenu> findAllByIdIn(final List<UUID> ids) {
        return menus.values()
                .stream()
                .filter(menu -> ids.contains(menu.getId()))
                .collect(Collectors.toList());
    }
}
