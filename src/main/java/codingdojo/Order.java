package codingdojo;

import lombok.*;

import java.util.*;

@Getter
@ToString
@AllArgsConstructor
public class Order {
    private final String id;
    private final Date date;
    private final Store store;
    private final Product[] products;

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
