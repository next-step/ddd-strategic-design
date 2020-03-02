package kitchenpos.bo;

import kitchenpos.domain.product.domain.dao.ProductDao;
import kitchenpos.domain.product.domain.model.Product;

import java.util.*;

public class InMemoryProductDao implements ProductDao {
    private final Map<Long, Product> entities = new HashMap<>();

    @Override
    public Product save(final Product entity) {
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<Product> findById(final Long id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(entities.values());
    }
}
