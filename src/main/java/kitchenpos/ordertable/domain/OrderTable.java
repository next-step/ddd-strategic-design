package kitchenpos.ordertable.domain;

public class OrderTable {
    private Long id;
    private Long tableGroupId;
    private Guest guest;
    private boolean empty;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getTableGroupId() {
        return tableGroupId;
    }

    public void setTableGroupId(final Long tableGroupId) {
        this.tableGroupId = tableGroupId;
    }

    public int getNumberOfGuests() {
        return guest.getNumberOfGuests();
    }

    public void setNumberOfGuests(final int numberOfGuests) {
        this.guest = new Guest(numberOfGuests);
    }
    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(final boolean empty) {
        this.empty = empty;
    }
}
