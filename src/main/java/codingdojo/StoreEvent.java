package codingdojo;

import lombok.*;

/**
 * StoreEvent is a ticket to an in-store event, e.g.,
 * makeover, eyeshadow masterclass,
 * or beauty product launch evening reception.
 */
@ToString
public class StoreEvent extends Product {
    public StoreEvent(String name, String id, Store location, Price price) {
        super(name, id, 0, price);
        setLocation(location);
    }

    public void setLocation(Store store) {
        store.addStoreEvent(this);
    }

    @Override
    public double getTaxRate() {
        return 0.25;
    }

    @Override
    public boolean isEvent() {
        return true;
    }
}
