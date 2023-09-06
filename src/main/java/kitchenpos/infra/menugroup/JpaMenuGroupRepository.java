package kitchenpos.infra.menugroup;

import kitchenpos.domain.menugroup.domain.MenuGroup;
import kitchenpos.domain.menugroup.domain.MenuGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaMenuGroupRepository extends MenuGroupRepository, JpaRepository<MenuGroup, UUID> {
}
