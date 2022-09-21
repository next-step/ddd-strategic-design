package kitchenpos.menu.domain.repository;

import java.util.UUID;
import kitchenpos.menu.domain.model.MenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMenuGroupRepository extends MenuGroupRepository, JpaRepository<MenuGroup, UUID> {
}
