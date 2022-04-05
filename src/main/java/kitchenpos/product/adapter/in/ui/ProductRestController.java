package kitchenpos.product.adapter.in.ui;

import kitchenpos.product.application.port.in.ProductUseCase;
import kitchenpos.product.domain.Product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/products")
@RestController
public class ProductRestController {
    private final ProductUseCase productUseCase;

    public ProductRestController(final ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody final Product request) {
        final Product response = productUseCase.create(request);
        return ResponseEntity.created(URI.create("/api/products/" + response.getId()))
                             .body(response);
    }

    @PutMapping("/{productId}/price")
    public ResponseEntity<Product> changePrice(@PathVariable final UUID productId, @RequestBody final Product request) {
        return ResponseEntity.ok(productUseCase.changePrice(productId, request));
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productUseCase.findAll());
    }
}
