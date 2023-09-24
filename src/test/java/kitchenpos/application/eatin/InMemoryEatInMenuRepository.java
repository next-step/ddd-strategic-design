package kitchenpos.application.eatin;

import kitchenpos.eatinorder.domain.EatInMenu;
import kitchenpos.eatinorder.domain.EatInMenuRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryEatInMenuRepository implements EatInMenuRepository {
    private final Map<UUID, EatInMenu> menus = new HashMap<>();

    @Override
    public EatInMenu save(final EatInMenu menu) {
        menus.put(menu.getId(), menu);
        return menu;
    }

    @Override
    public Optional<EatInMenu> findById(final UUID id) {
        return Optional.ofNullable(menus.get(id));
    }

    @Override
    public List<EatInMenu> findAll() {
        return new ArrayList<>(menus.values());
    }

    @Override
    public List<EatInMenu> findAllByIdIn(final List<UUID> ids) {
        return menus.values()
            .stream()
            .filter(menu -> ids.contains(menu.getId()))
            .collect(Collectors.toList());
    }

}
