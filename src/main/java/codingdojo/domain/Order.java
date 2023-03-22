package codingdojo.domain;

import codingdojo.*;
import codingdojo.xml.*;

import java.text.*;
import java.util.*;

public record Order(String id, Date date, Store store, Product[] products) {
    private static final Date TAX_CHANGE = DateUtil.fromIsoDate("2018-01-01T00:00Z");

    public void writeFullXml(StringBuilder xml) {
        xml.append(fullXml());
    }

    public XmlTag fullXml() {
        return XmlTag.builder()
                .withName("order")
                .withParameter(XmlParameter.of("id", id))
                .withChildren(Arrays.stream(products)
                        .map(Product::fullXml)
                        .toList())
                .build();
    }

    public void writeTaxDetailsXml(StringBuilder xml) {
        xml.append(taxDetailsXml());
    }

    public XmlTag taxDetailsXml() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return XmlTag.builder()
                .withName("order")
                .withParameter(XmlParameter.of("date", DateUtil.toIsoDate(date)))
                .withChildren(Arrays.stream(products)
                        .map(Product::basicXml)
                        .toList())
                .withChild(XmlTag.builder()
                        .withName("orderTax")
                        .withParameter(XmlParameter.of("currency", "USD"))
                        .withValue(formatter.format(getTaxInDollars()))
                        .build())
                .build();
    }

    public void writeHistoryXml(StringBuilder xml) {
        xml.append(historyXml());
    }

    public XmlTag historyXml() {
        return XmlTag.builder()
                .withName("order")
                .withParameter(XmlParameter.of("date", DateUtil.toIsoDate(date)))
                .withParameter(XmlParameter.of("totalDollars", String.valueOf(totalDollars())))
                .withChildren(Arrays.stream(products)
                        .map(Product::basicXml)
                        .toList())
                .build();
    }

    double totalDollars() {
        return Arrays.stream(products)
                .mapToDouble(Product::getPriceInDollars)
                .sum();
    }

    double getTaxInDollars() {
        return initialTaxInDollars() + Arrays.stream(products)
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
