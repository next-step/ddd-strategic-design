package kitchenpos.tobe.product.adapter.out.persistance;

import kitchenpos.tobe.product.application.port.out.ProductRepository;
import kitchenpos.tobe.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaProductRepository extends ProductRepository, JpaRepository<Product, UUID> {
}
