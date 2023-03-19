package codingdojo;

import java.text.*;
import java.util.*;

import static codingdojo.Price.Currency.USD;

public class XMLExporter {
    public static String exportFull(Collection<Order> orders) {
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<orders>");
        for (Order order : orders) {
            xml.append("<order");
            xml.append(" id='");
            xml.append(order.id());
            xml.append("'>");
            for (Product product : order.products()) {
                xml.append("<product");
                xml.append(" id='");
                xml.append(product.getId());
                xml.append("'");
                if (product.isEvent()) {
                    xml.append(" stylist='");
                    xml.append(stylistFor(product));
                    xml.append("'");
                }

                if (product.getWeight() > 0) {
                    xml.append(" weight='");
                    xml.append(product.getWeight());
                    xml.append("'");
                }

                xml.append(">");
                xml.append("<price");
                xml.append(" currency='");
                xml.append(product.getPrice().currency().toString());
                xml.append("'>");
                xml.append(product.getPrice().amount());
                xml.append("</price>");
                xml.append(product.getName());
                xml.append("</product>");
            }

            xml.append("</order>");
        }

        xml.append("</orders>");
        return xml.toString();
    }

    public static String exportTaxDetails(Collection<Order> orders) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<orderTax>");
        for (Order order : orders) {
            xml.append("<order");
            xml.append(" date='");
            xml.append(DateUtil.toIsoDate(order.date()));
            xml.append("'");
            xml.append(">");
            double tax = 0D;
            for (Product product : order.products()) {
                xml.append("<product");
                xml.append(" id='");
                xml.append(product.getId());
                xml.append("'");
                xml.append(">");
                xml.append(product.getName());
                xml.append("</product>");
                if (product.isEvent())
                    tax += product.getPriceInCurrency(USD) * 0.25;
                else
                    tax += product.getPriceInCurrency(USD) * 0.175;

            }

            xml.append("<orderTax currency='USD'>");
            if (order.date().before(DateUtil.fromIsoDate("2018-01-01T00:00Z")))
                tax += 10;
            else
                tax += 20;
            xml.append(formatter.format(tax));
            xml.append("</orderTax>");
            xml.append("</order>");
        }

        double totalTax = TaxCalculator.calculateAddedTaxInDollars(orders);
        xml.append(formatter.format(totalTax));
        xml.append("</orderTax>");
        return xml.toString();
    }

    public static String exportStore(Store store) {
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        xml.append("<store");
        xml.append(" name='");
        xml.append(store.getName());
        xml.append("'");
        xml.append(">");
        for (Product product : store.getStock()) {
            xml.append("<product");
            xml.append(" id='");
            xml.append(product.getId());
            xml.append("'");
            if (product.isEvent()) {
                xml.append(" location='");
                xml.append(store.getName());
                xml.append("'");
            } else {
                xml.append(" weight='");
                xml.append(product.getWeight());
                xml.append("'");
            }

            xml.append(">");
            xml.append("<price");
            xml.append(" currency='");
            xml.append(product.getPrice().currency().toString());
            xml.append("'>");
            xml.append(product.getPrice().amount());
            xml.append("</price>");
            xml.append(product.getName());
            xml.append("</product>");
        }

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
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
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
            for (Product product : order.products()) {
                xml.append("<product");
                xml.append(" id='");
                xml.append(product.getId());
                xml.append("'");
                xml.append(">");
                xml.append(product.getName());
                xml.append("</product>");
            }

            xml.append("</order>");
        }

        xml.append("</orderHistory>");
        return xml.toString();
    }

    private static String stylistFor(Product ignoredProduct) {
        // In the future, we will look up the name of the stylist from the database
        return "John Doe";
    }
}
