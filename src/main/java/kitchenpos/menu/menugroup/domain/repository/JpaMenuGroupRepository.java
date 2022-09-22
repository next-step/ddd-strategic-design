package kitchenpos.menu.menugroup.domain.repository;

import java.util.UUID;
import kitchenpos.menu.menugroup.domain.model.MenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMenuGroupRepository extends MenuGroupRepository, JpaRepository<MenuGroup, UUID> {
}
