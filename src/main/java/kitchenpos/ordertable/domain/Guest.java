package kitchenpos.ordertable.domain;

public class Guest {
    final private int numberOfGuests;

    public Guest(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
    public int getNumberOfGuests() {
        return this.numberOfGuests;
    }
}
