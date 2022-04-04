package kitchenpos.product.application.port.in;

import java.util.List;
import java.util.UUID;

import kitchenpos.product.domain.Product;

public interface ProductServicePort {
    Product create(Product request);

    Product changePrice(UUID productId, Product request);

    List<Product> findAll();
}
