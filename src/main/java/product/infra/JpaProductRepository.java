package product.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import product.domain.Product;
import product.domain.ProductRepository;

import java.util.UUID;

public interface JpaProductRepository extends ProductRepository, JpaRepository<Product, UUID> {
}
