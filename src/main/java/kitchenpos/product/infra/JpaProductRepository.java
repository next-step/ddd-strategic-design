package kitchenpos.product.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

import kitchenpos.product.domain.Product;
import kitchenpos.product.domain.ProductRepository;

public interface JpaProductRepository extends ProductRepository, JpaRepository<Product, UUID> {
}
