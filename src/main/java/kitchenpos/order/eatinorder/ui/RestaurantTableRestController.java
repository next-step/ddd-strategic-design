package kitchenpos.order.eatinorder.ui;

import kitchenpos.order.eatinorder.application.RestaurantTableService;
import kitchenpos.order.eatinorder.domain.RestaurantTable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/restaurant-tables")
@RestController
public class RestaurantTableRestController {
    private final RestaurantTableService restaurantTableService;

    public RestaurantTableRestController(final RestaurantTableService restaurantTableService) {
        this.restaurantTableService = restaurantTableService;
    }

    @PostMapping
    public ResponseEntity<RestaurantTable> create(@RequestBody final RestaurantTable request) {
        final RestaurantTable response = restaurantTableService.create(request);
        return ResponseEntity.created(URI.create("/api/restaurant-tables/" + response.getId()))
                .body(response);
    }

    @PutMapping("/{restaurantTableId}/sit")
    public ResponseEntity<RestaurantTable> sit(@PathVariable final UUID restaurantTableId) {
        return ResponseEntity.ok(restaurantTableService.sit(restaurantTableId));
    }

    @PutMapping("/{restaurantTableId}/clear")
    public ResponseEntity<RestaurantTable> clear(@PathVariable final UUID restaurantTableId) {
        return ResponseEntity.ok(restaurantTableService.clear(restaurantTableId));
    }

    @PutMapping("/{restaurantTableId}/number-of-guests")
    public ResponseEntity<RestaurantTable> changeNumberOfGuests(
            @PathVariable final UUID restaurantTableId,
            @RequestBody final RestaurantTable request
    ) {
        return ResponseEntity.ok(restaurantTableService.changeNumberOfGuests(restaurantTableId, request));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantTable>> findAll() {
        return ResponseEntity.ok(restaurantTableService.findAll());
    }
}
