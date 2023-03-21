package codingdojo;

import java.text.*;
import java.util.*;

public class XMLExporter {
    public static String exportFull(Collection<Order> orders) {
        StringBuilder xml = new StringBuilder();
        writeXmlHeader(xml);
        xml.append("<orders>");
        for (Order order : orders)
            order.writeFullXml(xml);

        xml.append("</orders>");
        return xml.toString();
    }

    public static String exportTaxDetails(Collection<Order> orders) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        StringBuilder xml = new StringBuilder();
        writeXmlHeader(xml);
        xml.append("<orderTax>");
        for (Order order : orders) {
            xml.append("<order");
            xml.append(" date='");
            xml.append(DateUtil.toIsoDate(order.date()));
            xml.append("'");
            xml.append(">");
            for (Product product : order.products())
                product.writeBasicXml(xml);

            xml.append("<orderTax currency='USD'>");
            xml.append(formatter.format(order.getTaxInDollars()));
            xml.append("</orderTax>");
            xml.append("</order>");
        }

        double totalTax = calculateAddedTaxInDollars(orders);
        xml.append(formatter.format(totalTax));
        xml.append("</orderTax>");
        return xml.toString();
    }

    public static String exportStore(Store store) {
        StringBuilder xml = new StringBuilder();
        writeXmlHeader(xml);

        xml.append("<store");
        xml.append(" name='");
        xml.append(store.getName());
        xml.append("'");
        xml.append(">");
        for (Product product : store.getStock())
            product.writeStockXml(xml);

        xml.append("</store>");

        return xml.toString();
    }

    @SuppressWarnings("unused") // public interface
    public static String exportHistory(Collection<Order> orders) {
        Date now = new Date();
        return exportHistory(orders, now);
    }

    static String exportHistory(Collection<Order> orders, Date date) {
        StringBuilder xml = new StringBuilder();
        writeXmlHeader(xml);
        xml.append("<orderHistory");
        xml.append(" createdAt='");
        xml.append(DateUtil.toIsoDate(date));
        xml.append("'");
        xml.append(">");
        for (Order order : orders) {
            xml.append("<order");
            xml.append(" date='");
            xml.append(DateUtil.toIsoDate(order.date()));
            xml.append("'");
            xml.append(" totalDollars='");
            xml.append(order.totalDollars());
            xml.append("'>");
            for (Product product : order.products())
                product.writeBasicXml(xml);

            xml.append("</order>");
        }

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
