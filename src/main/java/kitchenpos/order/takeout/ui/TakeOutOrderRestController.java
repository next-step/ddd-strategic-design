package kitchenpos.order.takeout.ui;

import kitchenpos.order.takeout.application.TakeOutOrderService;
import kitchenpos.order.takeout.domain.TakeOutOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/take-out-orders")
@RestController
public class TakeOutOrderRestController {
    private final TakeOutOrderService takeOutOrderService;

    public TakeOutOrderRestController(TakeOutOrderService takeOutOrderService) {
        this.takeOutOrderService = takeOutOrderService;
    }

    @PostMapping
    public ResponseEntity<TakeOutOrder> create(@RequestBody final TakeOutOrder request) {
        final TakeOutOrder response = takeOutOrderService.create(request);
        return ResponseEntity.created(URI.create("/api/take-out-orders/" + response.getId()))
                .body(response);
    }

    @PutMapping("/{takeoutOrderId}/accept")
    public ResponseEntity<TakeOutOrder> accept(@PathVariable final UUID takeoutOrderId) {
        return ResponseEntity.ok(takeOutOrderService.accept(takeoutOrderId));
    }

    @PutMapping("/{takeoutOrderId}/pickup")
    public ResponseEntity<TakeOutOrder> pickup(@PathVariable final UUID takeoutOrderId) {
        return ResponseEntity.ok(takeOutOrderService.pickup(takeoutOrderId));
    }

    @PutMapping("/{takeoutOrderId}/complete")
    public ResponseEntity<TakeOutOrder> complete(@PathVariable final UUID takeoutOrderId) {
        return ResponseEntity.ok(takeOutOrderService.complete(takeoutOrderId));
    }

    @GetMapping
    public ResponseEntity<List<TakeOutOrder>> findAll() {
        return ResponseEntity.ok(takeOutOrderService.findAll());
    }
}
