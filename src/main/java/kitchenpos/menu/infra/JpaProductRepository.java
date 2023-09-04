package kitchenpos.menu.infra;

import kitchenpos.menu.domain.Product;
import kitchenpos.menu.domain.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaProductRepository extends ProductRepository, JpaRepository<Product, UUID> {
}
