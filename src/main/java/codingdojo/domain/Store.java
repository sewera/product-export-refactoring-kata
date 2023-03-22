package codingdojo.domain;

import codingdojo.xml.*;
import lombok.*;

import java.util.*;

/**
 * Represents a physical Store where you can go and buy
 * products and attend events.
 */
@Getter
@ToString
public class Store {
    private final Set<Product> itemsInStock = new HashSet<>();
    private final String name;
    private final String id;

    public Store(String name, String id, Collection<Product> products) {
        this.name = name;
        this.id = id;
        itemsInStock.addAll(products);
    }

    public XmlTag stockXml() {
        return XmlTag.builder()
                .withName("store")
                .withParameter(XmlParameter.of("name", name))
                .withChildren(itemsInStock.stream()
                        .map(Product::stockXml)
                        .toList())
                .build();
    }

    public void addStockedItems(Product... items) {
        itemsInStock.addAll(Arrays.asList(items));
    }

    public void addStoreEvent(StoreEvent storeEvent) {
        itemsInStock.add(storeEvent);
    }

    @SuppressWarnings("unused")
    public void saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}
