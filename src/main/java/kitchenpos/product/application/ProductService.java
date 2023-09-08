package kitchenpos.product.application;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.menu.application.port.out.MenuRepository;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.product.application.port.in.ProductUseCase;
import kitchenpos.product.application.port.out.ProductRepository;
import kitchenpos.product.application.port.out.PurgomalumChecker;
import kitchenpos.product.domain.Product;


public class ProductService implements ProductUseCase {

}
