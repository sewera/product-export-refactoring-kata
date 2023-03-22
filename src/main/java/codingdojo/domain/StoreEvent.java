package codingdojo.domain;

import codingdojo.xml.*;

/**
 * StoreEvent is a ticket to an in-store event, e.g.,
 * makeover, eyeshadow masterclass,
 * or beauty product launch evening reception.
 */
public class StoreEvent extends Product {
    private final Store location;

    public StoreEvent(String name, String id, Store location, Money price) {
        super(name, id, price);
        this.location = location;
        setLocation(location);
    }

    @Override
    public XmlTag fullXml() {
        return xmlWithPrice().toBuilder()
                .withParameter(XmlParameter.of("stylist", getStylist()))
                .build();
    }

    @Override
    public XmlTag stockXml() {
        return xmlWithPrice().toBuilder()
                .withParameter(XmlParameter.of("location", location.getName()))
                .build();
    }

    @SuppressWarnings("SameReturnValue")
    private static String getStylist() {
        // In the future, we will look up the name of the stylist from the database
        return "John Doe";
    }

    public void setLocation(Store store) {
        store.addStockedItems(this);
    }

    @Override
    public double taxRate() {
        return 0.25;
    }
}
