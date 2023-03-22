package codingdojo.domain;

import codingdojo.*;
import codingdojo.xml.*;
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

    public XmlTag fullXml() {
        return XmlTag.builder()
                .withName("orders")
                .withChildren(orders.stream()
                        .map(Order::fullXml)
                        .toList())
                .build();
    }

    public XmlTag taxDetailsXml() {
        var formatter = new DecimalFormat("#0.00");
        return XmlTag.builder()
                .withName("orderTax")
                .withChildren(orders.stream()
                        .map(Order::taxDetailsXml)
                        .toList())
                .withValue(formatter.format(getTaxInDollars()))
                .build();
    }

    public XmlTag historyXml(Date dateOfCreation) {
        return XmlTag.builder()
                .withName("orderHistory")
                .withParameter(XmlParameter.of("createdAt", DateUtil.toIsoDate(dateOfCreation)))
                .withChildren(orders.stream()
                        .map(Order::historyXml)
                        .toList())
                .build();
    }

    double getTaxInDollars() {
        return orders.stream().mapToDouble(Order::taxInDollars).sum();
    }
}
