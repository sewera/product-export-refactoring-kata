package codingdojo;

import java.util.*;

import static codingdojo.Price.Currency.USD;

public record Order(String id, Date date, Store store, Product[] products) {
    private static final Date TAX_CHANGE = DateUtil.fromIsoDate("2018-01-01T00:00Z");

    public double totalDollars() {
        return Arrays.stream(products)
                .mapToDouble(product -> product.getPriceInCurrency(USD))
                .sum();
    }

    public double getTax() {
        return initialTax() + Arrays.stream(products)
                .mapToDouble(Product::getTax)
                .sum();
    }

    public double initialTax() {
        return date.before(TAX_CHANGE) ? 10.0 : 20.0;
    }

    @SuppressWarnings("unused")
    public void saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}
