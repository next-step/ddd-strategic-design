package kitchenpos.eatinorder.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JpaEatInMenuRepository extends EatInMenuRepository, JpaRepository<EatInMenu, UUID> {
    @Query("select m from EatInMenu m join m.menuProducts mp where mp.product.id = :productId")
    @Override
    List<EatInMenu> findAllByProductId(@Param("productId") UUID productId);
}
