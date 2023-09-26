package kitchenpos.application.takeout;


import kitchenpos.takeoutorder.domain.TakeOutMenu;
import kitchenpos.takeoutorder.domain.TakeOutMenuRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryTakeOutMenuRepository implements TakeOutMenuRepository {
    private final Map<UUID, TakeOutMenu> menus = new HashMap<>();

    @Override
    public TakeOutMenu save(final TakeOutMenu menu) {
        menus.put(menu.getId(), menu);
        return menu;
    }

    @Override
    public Optional<TakeOutMenu> findById(final UUID id) {
        return Optional.ofNullable(menus.get(id));
    }

    @Override
    public List<TakeOutMenu> findAll() {
        return new ArrayList<>(menus.values());
    }

    @Override
    public List<TakeOutMenu> findAllByIdIn(final List<UUID> ids) {
        return menus.values()
                .stream()
                .filter(menu -> ids.contains(menu.getId()))
                .collect(Collectors.toList());
    }

}
