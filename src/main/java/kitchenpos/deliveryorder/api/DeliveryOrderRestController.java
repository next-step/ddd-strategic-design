package kitchenpos.deliveryorder.api;

import kitchenpos.deliveryorder.application.DeliverOrderService;
import kitchenpos.deliveryorder.domain.DeliveryOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/deliver-orders")
@RestController
public class DeliveryOrderRestController {
    private final DeliverOrderService deliverOrderService;

    public DeliveryOrderRestController(final DeliverOrderService deliverOrderService) {
        this.deliverOrderService = deliverOrderService;
    }

    @PostMapping
    public ResponseEntity<DeliveryOrder> create(@RequestBody final DeliveryOrder request) {
        final DeliveryOrder response = deliverOrderService.create(request);
        return ResponseEntity.created(URI.create("/api/orders/" + response.getId()))
                .body(response);
    }

    @PutMapping("/{orderId}/accept")
    public ResponseEntity<DeliveryOrder> accept(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(deliverOrderService.accept(orderId));
    }

    @PutMapping("/{orderId}/serve")
    public ResponseEntity<DeliveryOrder> serve(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(deliverOrderService.serve(orderId));
    }

    @PutMapping("/{orderId}/start-delivery")
    public ResponseEntity<DeliveryOrder> startDelivery(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(deliverOrderService.startDelivery(orderId));
    }

    @PutMapping("/{orderId}/complete-delivery")
    public ResponseEntity<DeliveryOrder> completeDelivery(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(deliverOrderService.completeDelivery(orderId));
    }

    @PutMapping("/{orderId}/complete")
    public ResponseEntity<DeliveryOrder> complete(@PathVariable final UUID orderId) {
        return ResponseEntity.ok(deliverOrderService.complete(orderId));
    }

    @GetMapping
    public ResponseEntity<List<DeliveryOrder>> findAll() {
        return ResponseEntity.ok(deliverOrderService.findAll());
    }
}
