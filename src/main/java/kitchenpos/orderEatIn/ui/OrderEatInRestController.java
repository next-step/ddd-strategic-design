package kitchenpos.orderEatIn.ui;

import kitchenpos.orderEatIn.application.OrderEatInService;
import kitchenpos.orderEatIn.domain.OrderEatIn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/orders")
@RestController
public class OrderEatInRestController {
    private final OrderEatInService orderService;

    public OrderEatInRestController(final OrderEatInService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderEatIn> create(@RequestBody final OrderEatIn request) {
        final OrderEatIn response = orderService.create(request);
        return ResponseEntity.created(URI.create("/api/orders/" + response.getId()))
            .body(response);
    }

    @PutMapping("/{orderId}/accept")
    public ResponseEntity<OrderEatIn> accept(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.accept(orderId));
    }

    @PutMapping("/{orderId}/serve")
    public ResponseEntity<OrderEatIn> serve(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.serve(orderId));
    }

    @PutMapping("/{orderId}/complete")
    public ResponseEntity<OrderEatIn> complete(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.complete(orderId));
    }

    @GetMapping
    public ResponseEntity<List<OrderEatIn>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }
}
