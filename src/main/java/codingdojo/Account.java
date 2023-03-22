package codingdojo;

import lombok.*;

import java.text.*;
import java.util.*;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public class Account {
    private final List<Order> orders;

    public static Account of(Collection<Order> orders) {
        return new Account(orders.stream().toList());
    }

    public void writeFullXml(StringBuilder xml) {
        xml.append("<orders>");
        orders.forEach(order -> order.writeFullXml(xml));
        xml.append("</orders>");
    }

    public void writeTaxDetailsXml(StringBuilder xml) {
        var formatter = new DecimalFormat("#0.00");
        xml.append("<orderTax>");
        orders.forEach(order -> order.writeTaxDetailsXml(xml));
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
        orders.forEach(order -> order.writeHistoryXml(xml));
        xml.append("</orderHistory>");
    }

    double getTaxInDollars() {
        return orders.stream().mapToDouble(Order::getTaxInDollars).sum();
    }
}
