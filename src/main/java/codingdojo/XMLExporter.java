package codingdojo;

import java.util.*;

public class XMLExporter {
    public static String exportFull(Account account) {
        var xml = new StringBuilder();
        writeXmlHeader(xml);
        account.writeFullXml(xml);
        return xml.toString();
    }

    public static String exportFull(Collection<Order> orders) {
        return exportFull(Account.of(orders));
    }

    public static String exportTaxDetails(Account account) {
        var xml = new StringBuilder();
        writeXmlHeader(xml);
        account.writeTaxDetailsXml(xml);
        return xml.toString();
    }

    public static String exportTaxDetails(Collection<Order> orders) {
        return exportTaxDetails(Account.of(orders));
    }

    public static String exportStore(Store store) {
        var xml = new StringBuilder();
        writeXmlHeader(xml);
        store.writeStockXml(xml);
        return xml.toString();
    }

    @SuppressWarnings("unused") // public interface
    public static String exportHistory(Collection<Order> orders) {
        var now = new Date();
        return exportHistory(orders, now);
    }

    static String exportHistory(Collection<Order> orders, Date dateOfCreation) {
        var xml = new StringBuilder();
        writeXmlHeader(xml);
        var account = Account.of(orders);
        account.writeHistoryXml(xml, dateOfCreation);
        return xml.toString();
    }

    private static void writeXmlHeader(StringBuilder xml) {
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    }
}
