package tobe.kitchenpos.order.delivery.ui;

import tobe.kitchenpos.order.common.domain.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tobe.kitchenpos.order.delivery.application.DeliveryOrderService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/orders/delivery")
@RestController
public class DeliveryOrderRestController {
    private final DeliveryOrderService deliveryOrderService;

    public DeliveryOrderRestController(final DeliveryOrderService deliveryOrderService) {
        this.deliveryOrderService = deliveryOrderService;
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody final Order request) {
        final Order response = deliveryOrderService.create(request);
        return ResponseEntity.created(URI.create("/api/orders/delivery/" + response.getId()))
            .body(response);
    }

    @PutMapping("/{orderId}/accept")
    public ResponseEntity<Order> accept(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(deliveryOrderService.accept(orderId));
    }

    @PutMapping("/{orderId}/serve")
    public ResponseEntity<Order> serve(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(deliveryOrderService.serve(orderId));
    }

    @PutMapping("/{orderId}/start-delivery")
    public ResponseEntity<Order> startDelivery(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(deliveryOrderService.startDelivery(orderId));
    }

    @PutMapping("/{orderId}/complete-delivery")
    public ResponseEntity<Order> completeDelivery(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(deliveryOrderService.completeDelivery(orderId));
    }

    @PutMapping("/{orderId}/complete")
    public ResponseEntity<Order> complete(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(deliveryOrderService.complete(orderId));
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(deliveryOrderService.findAll());
    }
}
