package codingdojo.domain;

import codingdojo.xml.*;

/**
 * StoreEvent is a ticket to an in-store event, e.g.,
 * makeover, eyeshadow masterclass,
 * or beauty product launch evening reception.
 */
public class StoreEvent extends Product {
    private final Store location;

    public StoreEvent(String name, String id, Money price, Store location) {
        super(name, id, price);
        this.location = location;
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
                .withParameter(XmlParameter.of("location", location.name()))
                .build();
    }

    @Override
    protected double taxRate() {
        return 0.25;
    }

    @SuppressWarnings("SameReturnValue")
    private static String getStylist() {
        // In the future, we will look up the name of the stylist from the database
        return "John Doe";
    }
}
