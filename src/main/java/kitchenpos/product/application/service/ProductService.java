package kitchenpos.product.application.service;

import kitchenpos.product.application.port.out.ProductPurgomalumClient;
import kitchenpos.menu.application.port.out.MenuRepository;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.product.application.port.in.ProductServicePort;
import kitchenpos.product.application.port.out.ProductRepository;
import kitchenpos.product.domain.Product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProductService implements ProductServicePort {
    private final ProductRepository productRepository;
    private final MenuRepository menuRepository;
    private final ProductPurgomalumClient productPurgomalumClient;

    public ProductService(
        final ProductRepository productRepository,
        final MenuRepository menuRepository,
        final ProductPurgomalumClient productPurgomalumClient
    ) {
        this.productRepository = productRepository;
        this.menuRepository = menuRepository;
        this.productPurgomalumClient = productPurgomalumClient;
    }

    @Override
    @Transactional
    public Product create(final Product request) {
        final BigDecimal price = request.getPrice();
        if (Objects.isNull(price) || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        final String name = request.getName();
        if (Objects.isNull(name) || productPurgomalumClient.containsProfanity(name)) {
            throw new IllegalArgumentException();
        }
        final Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName(name);
        product.setPrice(price);
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product changePrice(final UUID productId, final Product request) {
        final BigDecimal price = request.getPrice();
        if (Objects.isNull(price) || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        final Product product = productRepository.findById(productId)
                                                 .orElseThrow(NoSuchElementException::new);
        product.setPrice(price);
        final List<Menu> menus = menuRepository.findAllByProductId(productId);
        for (final Menu menu : menus) {
            BigDecimal sum = BigDecimal.ZERO;
            for (final MenuProduct menuProduct : menu.getMenuProducts()) {
                sum = menuProduct.getProduct()
                                 .getPrice()
                                 .multiply(BigDecimal.valueOf(menuProduct.getQuantity()));
            }
            if (menu.getPrice().compareTo(sum) > 0) {
                menu.setDisplayed(false);
            }
        }
        return product;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
