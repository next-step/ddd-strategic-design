package kitchenpos.menu.adapter.in.ui;

import kitchenpos.menu.application.port.in.MenuServicePort;
import kitchenpos.menu.domain.Menu;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/menus")
@RestController
public class MenuRestController {
    private final MenuServicePort menuServicePort;

    public MenuRestController(final MenuServicePort menuServicePort) {
        this.menuServicePort = menuServicePort;
    }

    @PostMapping
    public ResponseEntity<Menu> create(@RequestBody final Menu request) {
        final Menu response = menuServicePort.create(request);
        return ResponseEntity.created(URI.create("/api/menus/" + response.getId()))
                             .body(response);
    }

    @PutMapping("/{menuId}/price")
    public ResponseEntity<Menu> changePrice(@PathVariable final UUID menuId, @RequestBody final Menu request) {
        return ResponseEntity.ok(menuServicePort.changePrice(menuId, request));
    }

    @PutMapping("/{menuId}/display")
    public ResponseEntity<Menu> display(@PathVariable final UUID menuId) {
        return ResponseEntity.ok(menuServicePort.display(menuId));
    }

    @PutMapping("/{menuId}/hide")
    public ResponseEntity<Menu> hide(@PathVariable final UUID menuId) {
        return ResponseEntity.ok(menuServicePort.hide(menuId));
    }

    @GetMapping
    public ResponseEntity<List<Menu>> findAll() {
        return ResponseEntity.ok(menuServicePort.findAll());
    }
}
