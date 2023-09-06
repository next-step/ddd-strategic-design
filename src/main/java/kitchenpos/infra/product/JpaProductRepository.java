package kitchenpos.infra.product;

import kitchenpos.domain.product.domain.Product;
import kitchenpos.domain.product.domain.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaProductRepository extends ProductRepository, JpaRepository<Product, UUID> {
}
