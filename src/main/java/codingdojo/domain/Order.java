package codingdojo.domain;

import codingdojo.*;
import codingdojo.xml.*;

import java.util.*;

public record Order(String id, Date date, List<Product> products) {
    private static final Date TAX_CHANGE = DateUtil.fromIsoDate("2018-01-01T00:00Z");

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
                .withChild(taxInDollars().fullXml("orderTax"))
                .build();
    }

    public XmlTag historyXml() {
        return xmlWithDate().toBuilder()
                .withParameter(XmlParameter.of("totalDollars", totalDollars().plainAmount()))
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

    Money totalDollars() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(Money::sum)
                .orElse(Money.dollars(0));
    }

    Money taxInDollars() {
        return Money.dollars(initialTaxInDollars() + products.stream()
                .mapToDouble(Product::taxInDollars)
                .sum());
    }

    private double initialTaxInDollars() {
        return date.before(TAX_CHANGE) ? 10.0 : 20.0;
    }

    @SuppressWarnings("unused")
    public void saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}
