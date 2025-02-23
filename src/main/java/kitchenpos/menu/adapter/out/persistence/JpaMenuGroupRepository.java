package kitchenpos.menu.adapter.out.persistence;

import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.menu.application.out.MenuGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaMenuGroupRepository extends MenuGroupRepository, JpaRepository<MenuGroup, UUID> {
}
