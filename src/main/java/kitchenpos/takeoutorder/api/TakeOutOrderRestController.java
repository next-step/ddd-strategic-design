package kitchenpos.takeoutorder.api;

import kitchenpos.takeoutorder.application.TakeOutOrderService;
import kitchenpos.takeoutorder.domain.TakeOutOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/take-out-orders")
@RestController
public class TakeOutOrderRestController {
    private final TakeOutOrderService takeOutOrderService;

    public TakeOutOrderRestController(final TakeOutOrderService takeOutOrderService) {
        this.takeOutOrderService = takeOutOrderService;
    }

    @PostMapping
    public ResponseEntity<TakeOutOrder> create(@RequestBody final TakeOutOrder request) {
        final TakeOutOrder response = takeOutOrderService.create(request);
        return ResponseEntity.created(URI.create("/api/take-out-orders/" + response.getId()))
            .body(response);
    }

    @PutMapping("/{takeOutOrderId}/accept")
    public ResponseEntity<TakeOutOrder> accept(@PathVariable final UUID takeOutOrderId) {
        return ResponseEntity.ok(takeOutOrderService.accept(takeOutOrderId));
    }

    @PutMapping("/{takeOutOrderId}/serve")
    public ResponseEntity<TakeOutOrder> serve(@PathVariable final UUID takeOutOrderId) {
        return ResponseEntity.ok(takeOutOrderService.serve(takeOutOrderId));
    }

    @PutMapping("/{takeOutOrderId}/complete")
    public ResponseEntity<TakeOutOrder> complete(@PathVariable final UUID takeOutOrderId) {
        return ResponseEntity.ok(takeOutOrderService.complete(takeOutOrderId));
    }

    @GetMapping
    public ResponseEntity<List<TakeOutOrder>> findAll() {
        return ResponseEntity.ok(takeOutOrderService.findAll());
    }
}
