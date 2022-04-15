package kitchenpos.menu.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.menu.domain.MenuGroupRepository;
import kitchenpos.menu.domain.Name;
import kitchenpos.menu.dto.MenuGroupRequest;
import kitchenpos.menu.dto.MenuGroupResonse;

@Service
public class MenuGroupService {
    private final MenuGroupRepository menuGroupRepository;

    public MenuGroupService(final MenuGroupRepository menuGroupRepository) {
        this.menuGroupRepository = menuGroupRepository;
    }

    @Transactional
    public MenuGroupResonse create(final MenuGroupRequest request) {
        Name name = new Name(request.getName());
        return MenuGroupResonse.of(menuGroupRepository.save(new MenuGroup(name)));
    }

    @Transactional(readOnly = true)
    public List<MenuGroupResonse> findAll() {
        return menuGroupRepository.findAll().stream()
			.map(MenuGroupResonse::of)
			.collect(Collectors.toList());
    }
}
