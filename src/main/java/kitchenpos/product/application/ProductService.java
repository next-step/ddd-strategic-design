package kitchenpos.product.application;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.product.domain.Name;
import kitchenpos.product.domain.Price;
import kitchenpos.product.domain.Product;
import kitchenpos.product.domain.ProductRepository;
import kitchenpos.product.dto.ProductRequest;
import kitchenpos.product.dto.ProductResponse;
import kitchenpos.product.infra.PurgomalumClient;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final PurgomalumClient purgomalumClient;

    public ProductService(
        final ProductRepository productRepository,
        final PurgomalumClient purgomalumClient
    ) {
        this.productRepository = productRepository;
        this.purgomalumClient = purgomalumClient;
    }

    @Transactional
    public ProductResponse create(final ProductRequest request) {
    	Name name = new Name(request.getName(), purgomalumClient);
    	Price price = new Price(request.getPrice());

        return ProductResponse.of(productRepository.save(new Product(name, price)));
    }

    @Transactional
    public ProductResponse changePrice(final UUID productId, final ProductRequest request) {
        final Product product = productRepository.findById(productId)
            .orElseThrow(NoSuchElementException::new);
        product.changePrice(new Price(request.getPrice()));
		// menuRepository.findAllByProductId(productId);
		// TODO : menu와 다른 context bound 인데 여기서 menu를 변경해서는 안됨
		// TODO : menu에서 해당 validation을 해주기 때문에 필요 없음

        return ProductResponse.of(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
			.map(ProductResponse::of)
			.collect(Collectors.toList());
    }
}
