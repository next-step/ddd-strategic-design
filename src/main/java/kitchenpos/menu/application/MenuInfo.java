package kitchenpos.menu.application;

import kitchenpos.menu.domain.Menu;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MenuInfo {

    private MenuInfo() {}

    public static class Response {

        private final UUID id;
        private final String name;
        private final BigDecimal price;
        private final MenuGroupInfo menuGroup;
        private final boolean displayed;
        private final List<MenuProductInfo> menuProducts;
        private final UUID menuGroupId;

        public Response(UUID id, String name, BigDecimal price, MenuGroupInfo menuGroup, boolean displayed, List<MenuProductInfo> menuProducts, UUID menuGroupId) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.menuGroup = menuGroup;
            this.displayed = displayed;
            this.menuProducts = menuProducts;
            this.menuGroupId = menuGroupId;
        }

        public static Response from(Menu menu) {
            MenuGroupInfo menuGroupInfo = new MenuGroupInfo(menu.getMenuGroup().getId(), menu.getMenuGroup().getName());
            List<MenuProductInfo> menuProductInfos = menu.getMenuProducts().stream().map(value -> {
                ProductInfo product = new ProductInfo(value.getProduct().getId(), value.getProduct().getName(), value.getProduct().getPrice());
                return new MenuProductInfo(value.getSeq(), product, value.getQuantity(), value.getProductId());
            }).collect(Collectors.toList());

            return new Response(menu.getId(), menu.getName(), menu.getPrice(), menuGroupInfo, menu.isDisplayed(), menuProductInfos, menu.getMenuGroupId());
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

        public MenuGroupInfo getMenuGroup() {
            return menuGroup;
        }

        public boolean isDisplayed() {
            return displayed;
        }

        public List<MenuProductInfo> getMenuProducts() {
            return menuProducts;
        }

        public UUID getMenuGroupId() {
            return menuGroupId;
        }
    }

    public static class MenuGroupInfo {
        private final UUID id;
        private final String name;

        public MenuGroupInfo(UUID id, String name) {
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

    public static class MenuProductInfo {
        private final Long seq;
        private final ProductInfo product;
        private final long quantity;
        private final UUID productId;

        public MenuProductInfo(Long seq, ProductInfo product, long quantity, UUID productId) {
            this.seq = seq;
            this.product = product;
            this.quantity = quantity;
            this.productId = productId;
        }

        public Long getSeq() {
            return seq;
        }

        public ProductInfo getProduct() {
            return product;
        }

        public long getQuantity() {
            return quantity;
        }

        public UUID getProductId() {
            return productId;
        }
    }

    public static class ProductInfo {
        private final UUID id;
        private final String name;
        private final BigDecimal price;

        public ProductInfo(UUID id, String name, BigDecimal price) {
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
