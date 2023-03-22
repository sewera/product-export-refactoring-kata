package codingdojo.domain;

import codingdojo.*;
import codingdojo.xml.*;
import lombok.*;

import java.util.*;

import static codingdojo.domain.Money.Currency.USD;

@Builder(setterPrefix = "with")
@AllArgsConstructor
public final class Order {
    private static final Date TAX_CHANGE = DateUtil.fromIsoDate("2018-01-01T00:00Z");

    private final String id;
    private final Date date;
    @Singular
    private final List<Product> products;

    public XmlTag fullXml() {
        return XmlTag.builder()
                .withName("order")
                .withParameter(XmlParameter.of("id", id))
                .withChildren(products.stream()
                        .map(Product::fullXml)
                        .toList())
                .build();
    }

    public XmlTag taxDetailsXml() {
        return xmlWithDate().toBuilder()
                .withChildren(products.stream()
                        .map(Product::basicXml)
                        .toList())
                .withChild(tax().fullXml("orderTax"))
                .build();
    }

    public XmlTag historyXml() {
        return xmlWithDate().toBuilder()
                .withParameter(XmlParameter.of("totalDollars", totalDollars()))
                .withChildren(products.stream()
                        .map(Product::basicXml)
                        .toList())
                .build();
    }

    private XmlTag xmlWithDate() {
        return XmlTag.builder()
                .withName("order")
                .withParameter(XmlParameter.of("date", DateUtil.toIsoDate(date)))
                .build();
    }

    private String totalDollars() {
        return totalPrice().plainAmountInCurrency(USD);
    }

    private Money totalPrice() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(Money::sum)
                .orElse(Money.dollars(0));
    }

    Money tax() {
        return Money.sum(initialTaxInDollars(), products.stream()
                .map(Product::tax)
                .reduce(Money::sum)
                .orElse(Money.dollars(0)));
    }

    private Money initialTaxInDollars() {
        return date.before(TAX_CHANGE) ? Money.dollars(10.0) : Money.dollars(20.0);
    }

    @SuppressWarnings("unused")
    public void saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}
