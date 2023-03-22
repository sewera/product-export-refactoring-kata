package codingdojo.domain;

import codingdojo.*;
import codingdojo.xml.*;
import lombok.*;

import java.util.*;

import static codingdojo.domain.Money.Currency.USD;
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
        return XmlTag.builder()
                .withName("orderTax")
                .withChildren(orders.stream()
                        .map(Order::taxDetailsXml)
                        .toList())
                .withValue(getTax().plainAmountInCurrency(USD))
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

    Money getTax() {
        return orders.stream()
                .map(Order::tax)
                .reduce(Money::sum)
                .orElse(Money.dollars(0));
    }
}
