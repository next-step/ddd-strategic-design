package kitchenpos.menu.infrastructure.persistence;

import kitchenpos.menu.domain.model.Product;
import kitchenpos.menu.domain.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaProductRepository extends ProductRepository, JpaRepository<Product, UUID> {
}
