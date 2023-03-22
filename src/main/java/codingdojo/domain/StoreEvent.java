package codingdojo.domain;

import lombok.*;

/**
 * StoreEvent is a ticket to an in-store event, e.g.,
 * makeover, eyeshadow masterclass,
 * or beauty product launch evening reception.
 */
@ToString
public class StoreEvent extends Product {
    private final Store location;

    public StoreEvent(String name, String id, Store location, Price price) {
        super(name, id, 0, price);
        this.location = location;
        setLocation(location);
    }

    @Override
    protected void writeDetailsForFullXml(StringBuilder xml) {
        xml.append(" stylist='");
        xml.append(getStylist());
        xml.append("'");
    }

    private static String getStylist() {
        // In the future, we will look up the name of the stylist from the database
        return "John Doe";
    }

    @Override
    public void writeStockXml(StringBuilder xml) {
        writeDetailedXml(xml, this::writeDetailsForStockXml);
    }

    private void writeDetailsForStockXml(StringBuilder xml) {
        xml.append(" location='");
        xml.append(location.getName());
        xml.append("'");
    }

    public void setLocation(Store store) {
        store.addStoreEvent(this);
    }

    @Override
    public double getTaxRate() {
        return 0.25;
    }

}
