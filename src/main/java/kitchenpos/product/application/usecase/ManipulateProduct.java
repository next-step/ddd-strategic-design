package kitchenpos.product.application.usecase;

import kitchenpos.product.domain.Product;

import java.util.UUID;

public interface ManipulateProduct {
    Product changePrice(final UUID productId, final Product request);
}
