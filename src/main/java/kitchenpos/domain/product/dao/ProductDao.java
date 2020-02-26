package kitchenpos.domain.product.dao;

import kitchenpos.domain.product.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    Product save(Product entity);

    Optional<Product> findById(Long id);

    List<Product> findAll();
}
