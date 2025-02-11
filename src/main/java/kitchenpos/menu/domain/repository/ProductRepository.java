package kitchenpos.menu.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import kitchenpos.menu.domain.model.Product;

public interface ProductRepository {
    Product save(Product product);

    Optional<Product> findById(UUID id);

    List<Product> findAll();

    List<Product> findAllByIdIn(List<UUID> ids);
}

