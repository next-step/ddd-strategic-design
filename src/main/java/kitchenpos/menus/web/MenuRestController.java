package kitchenpos.menus.web;

import java.net.URI;
import java.util.List;
import kitchenpos.menus.domain.usecase.MenuBo;
import kitchenpos.menus.domain.entity.Menu;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuRestController {

    private final MenuBo menuBo;

    public MenuRestController(final MenuBo menuBo) {
        this.menuBo = menuBo;
    }

    @PostMapping("/api/menus")
    public ResponseEntity<Menu> create(@RequestBody final Menu menu) {
        final Menu created = menuBo.create(menu);
        final URI uri = URI.create("/api/menus/" + created.getId());
        return ResponseEntity.created(uri)
            .body(created)
            ;
    }

    @GetMapping("/api/menus")
    public ResponseEntity<List<Menu>> list() {
        return ResponseEntity.ok()
            .body(menuBo.list())
            ;
    }
}
