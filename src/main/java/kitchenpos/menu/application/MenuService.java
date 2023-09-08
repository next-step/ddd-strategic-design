package kitchenpos.menu.application;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.menu.application.port.in.MenuUseCase;
import kitchenpos.menu.application.port.out.MenuGroupRepository;
import kitchenpos.menu.application.port.out.MenuRepository;
import kitchenpos.menu.application.port.out.PurgomalumChecker;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.product.application.port.out.ProductRepository;
import kitchenpos.product.domain.Product;


public class MenuService implements MenuUseCase {

}
