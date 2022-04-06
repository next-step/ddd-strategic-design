package kitchenpos.order.adapter.in.ui;

import kitchenpos.order.application.port.in.OrderUseCase;
import kitchenpos.order.domain.Order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/orders")
@RestController
public class OrderRestController {
    private final OrderUseCase orderUseCase;

    OrderRestController(final OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody final Order request) {
        final Order response = orderUseCase.create(request);
        return ResponseEntity.created(URI.create("/api/orders/" + response.getId()))
                             .body(response);
    }

    @PutMapping("/{orderId}/accept")
    public ResponseEntity<Order> accept(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderUseCase.accept(orderId));
    }

    @PutMapping("/{orderId}/serve")
    public ResponseEntity<Order> serve(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderUseCase.serve(orderId));
    }

    @PutMapping("/{orderId}/start-delivery")
    public ResponseEntity<Order> startDelivery(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderUseCase.startDelivery(orderId));
    }

    @PutMapping("/{orderId}/complete-delivery")
    public ResponseEntity<Order> completeDelivery(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderUseCase.completeDelivery(orderId));
    }

    @PutMapping("/{orderId}/complete")
    public ResponseEntity<Order> complete(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderUseCase.complete(orderId));
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(orderUseCase.findAll());
    }
}
