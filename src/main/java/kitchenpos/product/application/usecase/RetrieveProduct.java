package kitchenpos.product.application.usecase;

import kitchenpos.product.domain.Product;

import java.util.List;

public interface RetrieveProduct {
    List<Product> findAll();
}
