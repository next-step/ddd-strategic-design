package kitchenpos.menu.domain;

import kitchenpos.menu.domain.menugroup.MenuGroup;
import kitchenpos.menu.domain.product.MenuProduct;
import kitchenpos.menu.domain.product.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MenuCommand {

    private MenuCommand() {}

    public static class MenuRequest {
        private final UUID id;
        private final String name;
        private final BigDecimal price;
        private final MenuGroupRequest menuGroup;
        private final boolean displayed;
        private final List<MenuProductRequest> menuProducts;
        private final UUID menuGroupId;

        public MenuRequest(UUID id, String name, BigDecimal price, MenuGroupRequest menuGroup, boolean displayed, List<MenuProductRequest> menuProducts, UUID menuGroupId) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.menuGroup = menuGroup;
            this.displayed = displayed;
            this.menuProducts = menuProducts;
            this.menuGroupId = menuGroupId;
        }

        public Menu toEntity() {
            MenuGroup menuGroupEntity = new MenuGroup(menuGroup.id, menuGroup.name);
            List<MenuProduct> menuProductEntities = menuProducts.stream().map(value -> {
                Product product = new Product(value.product.id, value.product.name, value.product.price);
                return new MenuProduct(value.seq, product, value.quantity, value.productId);
            }).collect(Collectors.toList());

            return new Menu(id, name, price, menuGroupEntity, displayed, menuProductEntities, menuGroupId);
        }
    }

    public static class MenuGroupRequest {
        private final UUID id;
        private final String name;

        public MenuGroupRequest(UUID id, String name) {
            this.id = id;
            this.name = name;
        }

        public UUID getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static class MenuProductRequest {
        private final Long seq;
        private final ProductRequest product;
        private final long quantity;
        private final UUID productId;

        public MenuProductRequest(Long seq, ProductRequest product, long quantity, UUID productId) {
            this.seq = seq;
            this.product = product;
            this.quantity = quantity;
            this.productId = productId;
        }

        public Long getSeq() {
            return seq;
        }

        public ProductRequest getProduct() {
            return product;
        }

        public long getQuantity() {
            return quantity;
        }

        public UUID getProductId() {
            return productId;
        }
    }

    public static class ProductRequest {
        private final UUID id;
        private final String name;
        private final BigDecimal price;

        public ProductRequest(UUID id, String name, BigDecimal price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public UUID getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public BigDecimal getPrice() {
            return price;
        }
    }

}
