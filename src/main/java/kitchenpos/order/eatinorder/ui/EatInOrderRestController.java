package kitchenpos.order.eatinorder.ui;

import kitchenpos.domain.Order;
import kitchenpos.order.eatinorder.application.EatInOrderService;
import kitchenpos.order.eatinorder.domain.EatInOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/eat-in-orders")
@RestController
public class EatInOrderRestController {
    private final EatInOrderService eatInOrderService;

    public EatInOrderRestController(final EatInOrderService eatInOrderService) {
        this.eatInOrderService = eatInOrderService;
    }

    @PostMapping
    public ResponseEntity<EatInOrder> create(@RequestBody final EatInOrder request) {
        final EatInOrder response = eatInOrderService.create(request);
        return ResponseEntity.created(URI.create("/api/eatinorders/" + response.getId()))
                .body(response);
    }

    @PutMapping("/{eatInOrderId}/accept")
    public ResponseEntity<EatInOrder> accept(@PathVariable final UUID eatInOrderId) {
        return ResponseEntity.ok(eatInOrderService.accept(eatInOrderId));
    }

    @PutMapping("/{eatInOrderId}/serve")
    public ResponseEntity<EatInOrder> serve(@PathVariable final UUID eatInOrderId) {
        return ResponseEntity.ok(eatInOrderService.serve(eatInOrderId));
    }

    @PutMapping("/{eatInOrderId}/complete")
    public ResponseEntity<EatInOrder> complete(@PathVariable final UUID eatInOrderId) {
        return ResponseEntity.ok(eatInOrderService.complete(eatInOrderId));
    }

    @GetMapping
    public ResponseEntity<List<EatInOrder>> findAll() {
        return ResponseEntity.ok(eatInOrderService.findAll());
    }
}
