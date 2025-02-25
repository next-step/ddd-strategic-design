package kitchenpos.products.adapter.out.persistence;

import java.util.UUID;
import kitchenpos.products.application.out.ProductRepository;
import kitchenpos.products.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends ProductRepository, JpaRepository<Product, UUID> {

}
