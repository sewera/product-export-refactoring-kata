package codingdojo.domain;

import codingdojo.xml.*;
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
    protected XmlParameter writeDetailsForFullXml(StringBuilder xml) {
        return XmlParameter.of("stylist", getStylist());
    }

    private static String getStylist() {
        // In the future, we will look up the name of the stylist from the database
        return "John Doe";
    }

    @Override
    public void writeStockXml(StringBuilder xml) {
        xml.append("<product");
        xml.append(" id='");
        xml.append(id);
        xml.append("'");
        var details = writeDetailsForStockXml(xml);
        xml.append(details);
        xml.append(">");
        price.writeFullXml(xml);
        xml.append(name);
        xml.append("</product>");
    }

    private XmlParameter writeDetailsForStockXml(StringBuilder xml) {
        return XmlParameter.of("location", location.getName());
    }

    public void setLocation(Store store) {
        store.addStoreEvent(this);
    }

    @Override
    public double getTaxRate() {
        return 0.25;
    }

}
