package kitchenpos.menu.domain.repository;

import kitchenpos.menu.domain.model.MenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaMenuGroupRepository extends MenuGroupRepository, JpaRepository<MenuGroup, UUID> {
}
