package kitchenpos.menu.infra;

import java.util.UUID;
import kitchenpos.menu.domain.model.MenuGroup;
import kitchenpos.menu.domain.repository.MenuGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMenuGroupRepository extends MenuGroupRepository, JpaRepository<MenuGroup, UUID> {
}
