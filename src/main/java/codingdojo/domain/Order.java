package codingdojo.domain;

import codingdojo.*;
import codingdojo.xml.*;

import java.text.*;
import java.util.*;

public record Order(String id, Date date, Store store, List<Product> products) {
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
        NumberFormat formatter = new DecimalFormat("#0.00");
        return xmlWithDate().toBuilder()
                .withChildren(products.stream()
                        .map(Product::basicXml)
                        .toList())
                .withChild(XmlTag.builder()
                        .withName("orderTax")
                        .withParameter(XmlParameter.of("currency", "USD"))
                        .withValue(formatter.format(taxInDollars()))
                        .build())
                .build();
    }

    public XmlTag historyXml() {
        return xmlWithDate().toBuilder()
                .withParameter(XmlParameter.of("totalDollars", String.valueOf(totalDollars())))
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

    double totalDollars() {
        return products.stream()
                .mapToDouble(Product::getPriceInDollars)
                .sum();
    }

    double taxInDollars() {
        return initialTaxInDollars() + products.stream()
                .mapToDouble(Product::getTaxInDollars)
                .sum();
    }

    private double initialTaxInDollars() {
        return date.before(TAX_CHANGE) ? 10.0 : 20.0;
    }

    @SuppressWarnings("unused")
    public void saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}
