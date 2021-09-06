package kitchenpos.infra.repository;

import kitchenpos.domain.menu.product.Product;
import kitchenpos.domain.menu.product.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaProductRepository extends ProductRepository, JpaRepository<Product, UUID> {
}
