package kitchenpos.menu.service;

import kitchenpos.menu.dao.InMemoryMenuGroupDao;
import kitchenpos.menu.domain.MenuGroupDao;
import kitchenpos.menu.domain.model.MenuGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static kitchenpos.Fixtures.twoChickens;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MenuGroupServiceTest {
    private final MenuGroupDao menuGroupDao = new InMemoryMenuGroupDao();

    private MenuGroupService menuGroupService;

    @BeforeEach
    void setUp() {
        menuGroupService = new MenuGroupService(menuGroupDao);
    }

    @DisplayName("메뉴 그룹을 등록할 수 있다.")
    @Test
    void create() {
        // given
        final MenuGroup expected = twoChickens();

        // when
        final MenuGroup actual = menuGroupService.create(expected);

        // then
        assertMenuGroup(expected, actual);
    }

    @DisplayName("메뉴 그룹의 목록을 조회할 수 있다.")
    @Test
    void list() {
        // given
        final MenuGroup twoChickens = menuGroupDao.save(twoChickens());

        // when
        final List<MenuGroup> actual = menuGroupService.list();

        // then
        assertThat(actual).containsExactlyInAnyOrderElementsOf(Arrays.asList(twoChickens));
    }

    private void assertMenuGroup(final MenuGroup expected, final MenuGroup actual) {
        assertThat(actual).isNotNull();
        assertAll(
                () -> assertThat(actual.getName()).isEqualTo(expected.getName())
        );
    }
}
