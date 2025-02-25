package kitchenpos.eatinorder.external;

import java.math.BigDecimal;
import java.util.UUID;

// Menu 컨텍스트의 데이터를 EatInOrder 컨텍스트에 전달하기 위한 DTO
public class MenuDTO {
    private UUID id;
    private String name;
    private BigDecimal price;
    private boolean displayed;

    public MenuDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isDisplayed() {
        return displayed;
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }
} 