package codingdojo;

import java.util.*;

import static codingdojo.Price.Currency.USD;

public record Order(String id, Date date, Store store, Product[] products) {
    public double totalDollars() {
        return Arrays.stream(products)
                .mapToDouble(product -> product.getPriceInCurrency(USD))
                .sum();
    }

    @SuppressWarnings("unused")
    public void saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}
