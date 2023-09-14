package kitchenpos.order.takeout.ui;

import kitchenpos.order.takeout.application.TakeOutOrderService;
import kitchenpos.order.takeout.domain.TakeOutOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/orders/takeout")
@RestController
public class TakeOutOrderRestController {
    private final TakeOutOrderService takeOutOrderService;

    public TakeOutOrderRestController(final TakeOutOrderService takeOutOrderService) {
        this.takeOutOrderService = takeOutOrderService;
    }

    @PostMapping
    public ResponseEntity<TakeOutOrder> create(@RequestBody final TakeOutOrder request) {
        final TakeOutOrder response = takeOutOrderService.create(request);
        return ResponseEntity.created(URI.create("/api/orders/takeout/" + response.getId()))
            .body(response);
    }

    @PutMapping("/{orderId}/accept")
    public ResponseEntity<TakeOutOrder> accept(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(takeOutOrderService.accept(orderId));
    }

    @PutMapping("/{orderId}/serve")
    public ResponseEntity<TakeOutOrder> serve(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(takeOutOrderService.serve(orderId));
    }

    @PutMapping("/{orderId}/complete")
    public ResponseEntity<TakeOutOrder> complete(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(takeOutOrderService.complete(orderId));
    }

    @GetMapping
    public ResponseEntity<List<TakeOutOrder>> findAll() {
        return ResponseEntity.ok(takeOutOrderService.findAll());
    }
}
