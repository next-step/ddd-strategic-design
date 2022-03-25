package kitchenpos.product.infra;

import kitchenpos.product.domain.repository.ProductRepository;
import kitchenpos.product.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaProductRepository extends ProductRepository, JpaRepository<Product, UUID> {
}
