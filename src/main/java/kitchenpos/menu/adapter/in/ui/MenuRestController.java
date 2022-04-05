package kitchenpos.menu.adapter.in.ui;

import kitchenpos.menu.application.port.in.MenuUseCase;
import kitchenpos.menu.domain.Menu;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/menus")
@RestController
public class MenuRestController {
    private final MenuUseCase menuUseCase;

    public MenuRestController(final MenuUseCase menuUseCase) {
        this.menuUseCase = menuUseCase;
    }

    @PostMapping
    public ResponseEntity<Menu> create(@RequestBody final Menu request) {
        final Menu response = menuUseCase.create(request);
        return ResponseEntity.created(URI.create("/api/menus/" + response.getId()))
                             .body(response);
    }

    @PutMapping("/{menuId}/price")
    public ResponseEntity<Menu> changePrice(@PathVariable final UUID menuId, @RequestBody final Menu request) {
        return ResponseEntity.ok(menuUseCase.changePrice(menuId, request));
    }

    @PutMapping("/{menuId}/display")
    public ResponseEntity<Menu> display(@PathVariable final UUID menuId) {
        return ResponseEntity.ok(menuUseCase.display(menuId));
    }

    @PutMapping("/{menuId}/hide")
    public ResponseEntity<Menu> hide(@PathVariable final UUID menuId) {
        return ResponseEntity.ok(menuUseCase.hide(menuId));
    }

    @GetMapping
    public ResponseEntity<List<Menu>> findAll() {
        return ResponseEntity.ok(menuUseCase.findAll());
    }
}
