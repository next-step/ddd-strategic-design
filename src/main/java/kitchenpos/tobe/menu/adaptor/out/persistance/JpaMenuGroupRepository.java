package kitchenpos.tobe.menu.adaptor.out.persistance;

import kitchenpos.tobe.menu.application.port.out.MenuGroupRepository;
import kitchenpos.tobe.menu.domain.MenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaMenuGroupRepository extends MenuGroupRepository, JpaRepository<MenuGroup, UUID> {
}
