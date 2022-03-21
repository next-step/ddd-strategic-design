package kitchenpos.menu.interfaces;

import kitchenpos.menu.application.MenuFacade;
import kitchenpos.menu.domain.MenuCommand;
import kitchenpos.menu.application.MenuInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/menus")
@RestController
public class MenuRestController {
    private final MenuFacade menuFacade;

    public MenuRestController(final MenuFacade menuFacade) {
        this.menuFacade = menuFacade;
    }

    @PostMapping
    public ResponseEntity<MenuInfo.Response> create(@RequestBody final MenuDto.MenuRequest request) {
        final MenuCommand.MenuRequest command = MenuDtoMapper.from(request);
        final MenuInfo.Response response = menuFacade.create(command);
        return ResponseEntity.created(URI.create("/api/menus/" + response.getId()))
            .body(response);
    }

    @PutMapping("/{menuId}/price")
    public ResponseEntity<MenuInfo.Response> changePrice(@PathVariable final UUID menuId, @RequestBody final MenuDto.MenuRequest request) {
        final MenuCommand.MenuRequest command = MenuDtoMapper.from(request);
        return ResponseEntity.ok(menuFacade.changePrice(menuId, command));
    }

    @PutMapping("/{menuId}/display")
    public ResponseEntity<MenuInfo.Response> display(@PathVariable final UUID menuId) {
        return ResponseEntity.ok(menuFacade.display(menuId));
    }

    @PutMapping("/{menuId}/hide")
    public ResponseEntity<MenuInfo.Response> hide(@PathVariable final UUID menuId) {
        return ResponseEntity.ok(menuFacade.hide(menuId));
    }

    @GetMapping
    public ResponseEntity<List<MenuInfo.Response>> findAll() {
        return ResponseEntity.ok(menuFacade.findAll());
    }
}
