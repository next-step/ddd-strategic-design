package kitchenpos.product.application.usecase;

import kitchenpos.product.domain.Product;

public interface CreateProduct {
    Product create(final Product request);
}
