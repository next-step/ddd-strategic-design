package kitchenpos.order.deliveryorder.ui;

import kitchenpos.order.deliveryorder.application.DeliveryOrderService;
import kitchenpos.order.deliveryorder.domain.DeliveryOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/delivery-orders")
@RestController
public class DeliveryOrderRestController {
    private final DeliveryOrderService deliveryOrderService;

    public DeliveryOrderRestController(final DeliveryOrderService deliveryOrderService) {
        this.deliveryOrderService = deliveryOrderService;
    }

    @PostMapping
    public ResponseEntity<DeliveryOrder> create(@RequestBody final DeliveryOrder request) {
        final DeliveryOrder response = deliveryOrderService.create(request);
        return ResponseEntity.created(URI.create("/api/orders/" + response.getId()))
                .body(response);
    }

    @PutMapping("/{orderId}/accept")
    public ResponseEntity<DeliveryOrder> accept(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(deliveryOrderService.accept(orderId));
    }

    @PutMapping("/{orderId}/serve")
    public ResponseEntity<DeliveryOrder> serve(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(deliveryOrderService.serve(orderId));
    }

    @PutMapping("/{orderId}/start-delivery")
    public ResponseEntity<DeliveryOrder> startDelivery(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(deliveryOrderService.startDelivery(orderId));
    }

    @PutMapping("/{orderId}/complete-delivery")
    public ResponseEntity<DeliveryOrder> completeDelivery(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(deliveryOrderService.completeDelivery(orderId));
    }

    @PutMapping("/{orderId}/complete")
    public ResponseEntity<DeliveryOrder> complete(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(deliveryOrderService.complete(orderId));
    }

    @GetMapping
    public ResponseEntity<List<DeliveryOrder>> findAll() {
        return ResponseEntity.ok(deliveryOrderService.findAll());
    }
}
