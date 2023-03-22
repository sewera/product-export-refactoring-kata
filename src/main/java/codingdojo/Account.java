package codingdojo;

import lombok.*;

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

    public double getTaxInDollars() {
        return orders.stream().mapToDouble(Order::getTaxInDollars).sum();
    }
}
