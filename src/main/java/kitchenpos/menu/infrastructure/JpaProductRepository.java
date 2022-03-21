package kitchenpos.menu.infrastructure;

import kitchenpos.menu.domain.product.Product;
import kitchenpos.menu.domain.product.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaProductRepository extends ProductRepository, JpaRepository<Product, UUID> {
}
