package kitchenpos.menu.adapter.in.ui;

import kitchenpos.menu.application.port.in.MenuGroupServicePort;
import kitchenpos.menu.domain.MenuGroup;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("/api/menu-groups")
@RestController
public class MenuGroupRestController {
    private final MenuGroupServicePort menuGroupPortService;

    public MenuGroupRestController(final MenuGroupServicePort menuGroupPortService) {
        this.menuGroupPortService = menuGroupPortService;
    }

    @PostMapping
    public ResponseEntity<MenuGroup> create(@RequestBody final MenuGroup request) {
        final MenuGroup response = menuGroupPortService.create(request);
        return ResponseEntity.created(URI.create("/api/menu-groups/" + response.getId()))
                             .body(response);
    }

    @GetMapping
    public ResponseEntity<List<MenuGroup>> findAll() {
        return ResponseEntity.ok(menuGroupPortService.findAll());
    }
}
