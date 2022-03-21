package kitchenpos.menu.interfaces;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class MenuDto {

    public static class MenuRequest {
        private UUID id;
        private String name;
        private BigDecimal price;
        private MenuGroupRequest menuGroup;
        private boolean displayed;
        private List<MenuProductRequest> menuProducts;
        private UUID menuGroupId;

        public UUID getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public MenuGroupRequest getMenuGroup() {
            return menuGroup;
        }

        public boolean isDisplayed() {
            return displayed;
        }

        public List<MenuProductRequest> getMenuProducts() {
            return menuProducts;
        }

        public UUID getMenuGroupId() {
            return menuGroupId;
        }
    }

    public static class MenuGroupRequest {
        private UUID id;
        private String name;

        public UUID getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static class MenuProductRequest {
        private Long seq;
        private ProductRequest product;
        private long quantity;
        private UUID productId;

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
        private UUID id;
        private String name;
        private BigDecimal price;

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
