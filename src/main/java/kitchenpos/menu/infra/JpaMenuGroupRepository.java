package kitchenpos.menu.infra;

import kitchenpos.menu.domain.menugroup.MenuGroup;
import kitchenpos.menu.domain.menugroup.MenuGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaMenuGroupRepository extends MenuGroupRepository, JpaRepository<MenuGroup, UUID> {
}
