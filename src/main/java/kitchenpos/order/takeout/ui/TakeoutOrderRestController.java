package kitchenpos.order.takeout.ui;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kitchenpos.order.takeout.application.TakeoutOrderService;
import kitchenpos.order.takeout.dto.TakeoutOrderRequest;
import kitchenpos.order.takeout.dto.TakeoutOrderResponse;

@RequestMapping("/api/orders")
@RestController
public class TakeoutOrderRestController {
    private final TakeoutOrderService orderService;

    public TakeoutOrderRestController(final TakeoutOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<TakeoutOrderResponse> create(@RequestBody final TakeoutOrderRequest request) {
        final TakeoutOrderResponse response = orderService.create(request);
        return ResponseEntity.created(URI.create("/api/orders/" + response.getId()))
            .body(response);
    }

    @PutMapping("/{orderId}/accept")
    public ResponseEntity<TakeoutOrderResponse> accept(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.accept(orderId));
    }

    @PutMapping("/{orderId}/serve")
    public ResponseEntity<TakeoutOrderResponse> serve(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.serve(orderId));
    }

    @PutMapping("/{orderId}/complete")
    public ResponseEntity<TakeoutOrderResponse> complete(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.complete(orderId));
    }

    @GetMapping
    public ResponseEntity<List<TakeoutOrderResponse>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }
}
