package codingdojo;

import lombok.*;

import java.text.*;
import java.util.*;

@AllArgsConstructor
public class Account {
    private final List<Order> orders;

    public static Account of(Collection<Order> orders) {
        return new Account(orders.stream().toList());
    }

    public void writeFullXml(StringBuilder xml) {
        xml.append("<orders>");
        for (var order : orders)
            order.writeFullXml(xml);
        xml.append("</orders>");
    }

    public void writeTaxDetailsXml(StringBuilder xml) {
        var formatter = new DecimalFormat("#0.00");
        xml.append("<orderTax>");
        for (var order : orders)
            order.writeTaxDetailsXml(xml);

        var totalTax = getTaxInDollars();
        xml.append(formatter.format(totalTax));
        xml.append("</orderTax>");
    }

    public void writeHistoryXml(StringBuilder xml, Date dateOfCreation) {
        xml.append("<orderHistory");
        xml.append(" createdAt='");
        xml.append(DateUtil.toIsoDate(dateOfCreation));
        xml.append("'");
        xml.append(">");
        for (var order : orders)
            order.writeHistoryXml(xml);
        xml.append("</orderHistory>");
    }

    public double getTaxInDollars() {
        return orders.stream().mapToDouble(Order::getTaxInDollars).sum();
    }
}
