package kitchenpos.products.domain.entity;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    Product save(Product entity);

    Optional<Product> findById(Long id);

    List<Product> findAll();
}
