package kitchenpos.product.domain.repository;

import java.util.UUID;
import kitchenpos.product.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends ProductRepository, JpaRepository<Product, UUID> {
}
