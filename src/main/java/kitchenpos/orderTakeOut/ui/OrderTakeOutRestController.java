package kitchenpos.orderTakeOut.ui;

import kitchenpos.orderTakeOut.application.OrderTakeOutService;
import kitchenpos.orderTakeOut.domain.OrderTakeOut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/orders")
@RestController
public class OrderTakeOutRestController {
    private final OrderTakeOutService orderService;

    public OrderTakeOutRestController(final OrderTakeOutService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderTakeOut> create(@RequestBody final OrderTakeOut request) {
        final OrderTakeOut response = orderService.create(request);
        return ResponseEntity.created(URI.create("/api/orders/" + response.getId()))
            .body(response);
    }

    @PutMapping("/{orderId}/accept")
    public ResponseEntity<OrderTakeOut> accept(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.accept(orderId));
    }

    @PutMapping("/{orderId}/serve")
    public ResponseEntity<OrderTakeOut> serve(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.serve(orderId));
    }

    @PutMapping("/{orderId}/complete")
    public ResponseEntity<OrderTakeOut> complete(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.complete(orderId));
    }

    @GetMapping
    public ResponseEntity<List<OrderTakeOut>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }
}
