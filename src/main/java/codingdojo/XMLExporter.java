package codingdojo;

import java.text.*;
import java.util.*;

public class XMLExporter {
    public static String exportFull(Collection<Order> orders) {
        var xml = new StringBuilder();
        writeXmlHeader(xml);
        var account = Account.of(orders);
        account.writeFullXml(xml);
        return xml.toString();
    }

    public static String exportTaxDetails(Collection<Order> orders) {
        var formatter = new DecimalFormat("#0.00");
        var xml = new StringBuilder();
        writeXmlHeader(xml);
        xml.append("<orderTax>");
        for (var order : orders)
            order.writeTaxDetailsXml(xml);

        var totalTax = calculateAddedTaxInDollars(orders);
        xml.append(formatter.format(totalTax));
        xml.append("</orderTax>");
        return xml.toString();
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

    static String exportHistory(Collection<Order> orders, Date date) {
        var xml = new StringBuilder();
        writeXmlHeader(xml);
        xml.append("<orderHistory");
        xml.append(" createdAt='");
        xml.append(DateUtil.toIsoDate(date));
        xml.append("'");
        xml.append(">");
        for (var order : orders)
            order.writeHistoryXml(xml);

        xml.append("</orderHistory>");
        return xml.toString();
    }

    private static void writeXmlHeader(StringBuilder xml) {
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    }

    private static double calculateAddedTaxInDollars(Collection<Order> orders) {
        return orders.stream()
                .mapToDouble(Order::getTaxInDollars)
                .sum();
    }
}
