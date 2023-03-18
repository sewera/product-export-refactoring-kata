package codingdojo;

import java.util.*;

public record Order(String id, Date date, Store store, Product[] products) {
    public double totalDollars() {
        return Arrays.stream(products)
                .mapToDouble(product -> product.getPrice().getAmountInCurrency("USD"))
                .sum();
    }

    @SuppressWarnings("unused")
    public void saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}
