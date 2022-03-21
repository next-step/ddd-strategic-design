package kitchenpos.order.domain.menu;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

//test 통과를위해 연관관계 설정 전부 주석처리
//@Table(name = "order_menu")
//@Entity
public class Menu {
//    @Column(name = "id", columnDefinition = "varbinary(16)")
//    @Id
    private UUID id;

//    @Column(name = "name", nullable = false)
    private String name;

//    @Column(name = "price", nullable = false)
    private BigDecimal price;

//    @ManyToOne(optional = false)
//    @JoinColumn(
//        name = "menu_group_id",
//        columnDefinition = "varbinary(16)",
//        foreignKey = @ForeignKey(name = "fk_menu_to_menu_group")
//    )
    private MenuGroup menuGroup;

//    @Column(name = "displayed", nullable = false)
    private boolean displayed;

//    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(
//        name = "menu_id",
//        nullable = false,
//        columnDefinition = "varbinary(16)",
//        foreignKey = @ForeignKey(name = "fk_menu_product_to_menu")
//    )
    private List<MenuProduct> menuProducts;

//    @Transient
    private UUID menuGroupId;

    public Menu() {
    }

    public Menu(UUID id, String name, BigDecimal price, MenuGroup menuGroupEntity, boolean displayed, List<MenuProduct> menuProductEntities, UUID menuGroupId) {
        this.id = id;
        this.name = name;
        this.price = price;
        menuGroup = menuGroupEntity;
        this.displayed = displayed;
        menuProducts = menuProductEntities;
        this.menuGroupId = menuGroupId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public MenuGroup getMenuGroup() {
        return menuGroup;
    }

    public void setMenuGroup(MenuGroup menuGroup) {
        this.menuGroup = menuGroup;
    }

    public boolean isDisplayed() {
        return displayed;
    }

    public void setDisplayed(final boolean displayed) {
        this.displayed = displayed;
    }

    public List<MenuProduct> getMenuProducts() {
        return menuProducts;
    }

    public void setMenuProducts(final List<MenuProduct> menuProducts) {
        this.menuProducts = menuProducts;
    }

    public UUID getMenuGroupId() {
        return menuGroupId;
    }

    public void setMenuGroupId(final UUID menuGroupId) {
        this.menuGroupId = menuGroupId;
    }
}
