package kitchenpos.product.application;

import kitchenpos.product.application.usecase.CreateProduct;
import kitchenpos.product.application.usecase.ManipulateProduct;
import kitchenpos.product.application.usecase.RetrieveProduct;

public interface ProductService extends CreateProduct, ManipulateProduct, RetrieveProduct {
}
