package codingdojo;

import lombok.*;

import java.util.*;

/**
 * Represents a physical Store where you can go and buy
 * products and attend events.
 */
@Getter
@ToString
public class Store {
    private final Map<String, Product> itemsInStock = new HashMap<>();
    private final String name;
    private final String id;

    public Store(String name, String id, Product[] products) {
        this.name = name;
        this.id = id;
        addStockedItems(products);
    }

    public void writeStockXml(StringBuilder xml) {
        xml.append("<store");
        xml.append(" name='");
        xml.append(name);
        xml.append("'");
        xml.append(">");
        itemsInStock.values().forEach(product -> product.writeStockXml(xml));
        xml.append("</store>");
    }

    public void addStockedItems(Product... items) {
        for (Product item : items) {
            itemsInStock.put(item.getName(), item);
        }
    }

    public void addStoreEvent(StoreEvent storeEvent) {
        itemsInStock.put(storeEvent.getName(), storeEvent);
    }

    public Collection<Product> getStock() {
        return Collections.unmodifiableCollection(itemsInStock.values());
    }

    @SuppressWarnings("unused")
    public void saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}
