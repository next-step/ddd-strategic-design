package kitchenpos.order.delivery.ui;

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

import kitchenpos.order.delivery.application.DeliveryOrderService;
import kitchenpos.order.delivery.dto.DeliveryOrderRequest;
import kitchenpos.order.delivery.dto.DeliveryOrderResponse;

@RequestMapping("/api/delivery-orders")
@RestController
public class DeliveryOrderRestController {
    private final DeliveryOrderService orderService;

    public DeliveryOrderRestController(final DeliveryOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<DeliveryOrderResponse> create(@RequestBody final DeliveryOrderRequest request) {
        final DeliveryOrderResponse response = orderService.create(request);
        return ResponseEntity.created(URI.create("/api/orders/" + response.getId()))
            .body(response);
    }

    @PutMapping("/{orderId}/accept")
    public ResponseEntity<DeliveryOrderResponse> accept(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.accept(orderId));
    }

    @PutMapping("/{orderId}/serve")
    public ResponseEntity<DeliveryOrderResponse> serve(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.serve(orderId));
    }

    @PutMapping("/{orderId}/start-delivery")
    public ResponseEntity<DeliveryOrderResponse> startDelivery(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.startDelivery(orderId));
    }

    @PutMapping("/{orderId}/complete-delivery")
    public ResponseEntity<DeliveryOrderResponse> completeDelivery(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.completeDelivery(orderId));
    }

    @PutMapping("/{orderId}/complete")
    public ResponseEntity<DeliveryOrderResponse> complete(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(orderService.complete(orderId));
    }

    @GetMapping
    public ResponseEntity<List<DeliveryOrderResponse>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }
}
