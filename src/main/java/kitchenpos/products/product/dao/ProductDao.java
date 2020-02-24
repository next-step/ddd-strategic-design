package kitchenpos.products.product.dao;

import java.util.List;
import java.util.Optional;
import kitchenpos.products.product.domain.Product;

public interface ProductDao {

    Product save(Product entity);

    Optional<Product> findById(Long id);

    List<Product> findAll();
}
