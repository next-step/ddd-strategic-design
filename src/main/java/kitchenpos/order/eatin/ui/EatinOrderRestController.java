package kitchenpos.order.eatin.ui;

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

import kitchenpos.order.eatin.application.EatinOrderService;
import kitchenpos.order.eatin.dto.EatinOrderResponse;
import kitchenpos.order.eatin.dto.EatinOrderRequest;

@RequestMapping("/api/eatin-orders")
@RestController
public class EatinOrderRestController {
    private final EatinOrderService orderService;

    public EatinOrderRestController(final EatinOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<EatinOrderResponse> create(@RequestBody final EatinOrderRequest request) {
        final EatinOrderResponse response = orderService.create(request);
        return ResponseEntity.created(URI.create("/api/orders/" + response.getId()))
            .body(response);
    }

    @PutMapping("/{orderId}/accept")
    public ResponseEntity<EatinOrderResponse> accept(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.accept(orderId));
    }

    @PutMapping("/{orderId}/serve")
    public ResponseEntity<EatinOrderResponse> serve(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.serve(orderId));
    }

    @PutMapping("/{orderId}/complete")
    public ResponseEntity<EatinOrderResponse> complete(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.complete(orderId));
    }

    @GetMapping
    public ResponseEntity<List<EatinOrderResponse>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }
}
