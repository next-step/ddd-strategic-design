package kitchenpos.menu.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "menu")
@Entity
public class Menu {
    @Column(name = "id", columnDefinition = "varbinary(16)")
    @Id
    private UUID id;

    @Embedded
    private DisplayName displayName;

    @Embedded
	private Price price;

    @ManyToOne(optional = false)
    @JoinColumn(
        name = "menu_group_id",
        columnDefinition = "varbinary(16)",
        foreignKey = @ForeignKey(name = "fk_menu_to_menu_group")
    )
    private MenuGroup menuGroup;

    @Column(name = "displayed", nullable = false)
    private boolean displayed;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
        name = "menu_id",
        nullable = false,
        columnDefinition = "varbinary(16)",
        foreignKey = @ForeignKey(name = "fk_menu_product_to_menu")
    )
    private List<MenuProduct> menuProducts;

    @Transient
    private UUID menuGroupId;

    public Menu() {
    }

    public Menu(DisplayName displayName, Price price, MenuGroup menuGroup, boolean displayed, List<MenuProduct> menuProducts) {
    	this.id = UUID.randomUUID();
    	this.displayName = displayName;
    	this.price = price;
    	this.menuGroup = menuGroup;
    	this.displayed = displayed;
    	this.menuProducts = validateMenuProducts(price, menuProducts);
	}

	private List<MenuProduct> validateMenuProducts(Price price, List<MenuProduct> menuProducts) {
		if (Objects.isNull(menuProducts) || menuProducts.isEmpty()) {
			throw new IllegalArgumentException();
		}

		if (price.isGreaterThan(getSum(menuProducts))) {
			throw new IllegalArgumentException();
		}

		return menuProducts;
	}

	private BigDecimal getSum(List<MenuProduct> menuProducts) {
		BigDecimal sum = BigDecimal.ZERO;
		for (final MenuProduct menuProduct : menuProducts) {
			sum = sum.add(menuProduct.getCost());
		}
		return sum;
	}

	public UUID getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName.getValue();
    }

    public BigDecimal getPrice() {
        return price.getValue();
    }

    public MenuGroup getMenuGroup() {
        return menuGroup;
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

	public Menu changePrice(Price price) {
		validateMenuProducts(price, menuProducts);

		return this;
	}

	public Menu display() {
		validateMenuProducts(price, menuProducts);
		displayed = true;

		return this;
	}

	public Menu hide() {
    	displayed = false;

		return this;
	}
}
