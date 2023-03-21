package codingdojo;

import java.text.*;
import java.util.*;

import static codingdojo.Price.Currency.USD;

public record Order(String id, Date date, Store store, Product[] products) {
    private static final Date TAX_CHANGE = DateUtil.fromIsoDate("2018-01-01T00:00Z");

    public void writeFullXml(StringBuilder xml) {
        xml.append("<order");
        xml.append(" id='");
        xml.append(id);
        xml.append("'>");
        Arrays.stream(products).forEach(product -> product.writeFullXml(xml));
        xml.append("</order>");
    }

    public void writeTaxDetailsXml(StringBuilder xml) {
        NumberFormat formatter = new DecimalFormat("#0.00");

        xml.append("<order");
        xml.append(" date='");
        xml.append(DateUtil.toIsoDate(date));
        xml.append("'");
        xml.append(">");
        Arrays.stream(products).forEach(product -> product.writeBasicXml(xml));

        xml.append("<orderTax currency='USD'>");
        xml.append(formatter.format(getTaxInDollars()));
        xml.append("</orderTax>");
        xml.append("</order>");
    }

    public double totalDollars() {
        return Arrays.stream(products)
                .mapToDouble(product -> product.getPriceInCurrency(USD))
                .sum();
    }

    public double getTaxInDollars() {
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
