package kitchenpos.orderDelivery.ui;

import kitchenpos.orderDelivery.application.OrderDeliveryService;
import kitchenpos.orderDelivery.domain.OrderDelivery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/orders")
@RestController
public class OrderDeliveryRestController {
    private final OrderDeliveryService orderService;

    public OrderDeliveryRestController(final OrderDeliveryService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDelivery> create(@RequestBody final OrderDelivery request) {
        final OrderDelivery response = orderService.create(request);
        return ResponseEntity.created(URI.create("/api/orders/" + response.getId()))
            .body(response);
    }

    @PutMapping("/{orderId}/accept")
    public ResponseEntity<OrderDelivery> accept(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.accept(orderId));
    }

    @PutMapping("/{orderId}/serve")
    public ResponseEntity<OrderDelivery> serve(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.serve(orderId));
    }

    @PutMapping("/{orderId}/start-delivery")
    public ResponseEntity<OrderDelivery> startDelivery(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.startDelivery(orderId));
    }

    @PutMapping("/{orderId}/complete-delivery")
    public ResponseEntity<OrderDelivery> completeDelivery(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.completeDelivery(orderId));
    }

    @PutMapping("/{orderId}/complete")
    public ResponseEntity<OrderDelivery> complete(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.complete(orderId));
    }

    @GetMapping
    public ResponseEntity<List<OrderDelivery>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }
}
