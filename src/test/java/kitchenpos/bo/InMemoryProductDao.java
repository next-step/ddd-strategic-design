package kitchenpos.bo;

import kitchenpos.product.model.ProductDao;
import kitchenpos.model.Product;

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
