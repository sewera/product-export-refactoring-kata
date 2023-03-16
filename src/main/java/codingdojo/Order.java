package codingdojo;

import lombok.*;

import java.util.*;

@ToString
public class Order {
    private final String id;
    private final Date date;
    private final Store store;
    private final Collection<Product> products = new ArrayList<>();

    public Order(String id, Date date, Store store, Product[] products) {
        this.id = id;
        this.date = date;
        this.store = store;
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

    public Collection<Product> getProducts() {
        return Collections.unmodifiableCollection(products);
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
