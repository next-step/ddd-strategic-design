package tobe.kitchenpos.order.ui.takeout;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tobe.kitchenpos.order.application.takeout.TakeOutOrderService;
import tobe.kitchenpos.order.domain.common.Order;

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
    public ResponseEntity<Order> create(@RequestBody final Order request) {
        final Order response = takeOutOrderService.create(request);
        return ResponseEntity.created(URI.create("/api/orders/takeout/" + response.getId()))
            .body(response);
    }

    @PutMapping("/{orderId}/accept")
    public ResponseEntity<Order> accept(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(takeOutOrderService.accept(orderId));
    }

    @PutMapping("/{orderId}/serve")
    public ResponseEntity<Order> serve(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(takeOutOrderService.serve(orderId));
    }

    @PutMapping("/{orderId}/complete")
    public ResponseEntity<Order> complete(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(takeOutOrderService.complete(orderId));
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(takeOutOrderService.findAll());
    }
}
