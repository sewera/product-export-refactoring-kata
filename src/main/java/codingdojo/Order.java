package codingdojo;

import lombok.*;

import java.util.*;

@ToString
@AllArgsConstructor
public class Order {
    private final String id;
    private final Date date;
    private final Store store;
    private final List<Product> products = new ArrayList<>();

    public Order(String id, Date date, Store store, Product[] products) {
        this(id, date, store);
        addProducts(products);
    }

    public double totalDollars() {
        return products.stream()
                .mapToDouble(product -> product.getPrice().getAmountInCurrency("USD"))
                .sum();
    }

    public String getId() {
        return id;
    }

    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    public Date getDate() {
        return date;
    }

    public void addProducts(Product... products) {
        this.products.addAll(Arrays.asList(products));
    }

    public Store getStore() {
        return store;
    }

    @SuppressWarnings("unused")
    public void saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}
