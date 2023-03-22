package codingdojo.domain;

import codingdojo.xml.*;
import lombok.*;

import java.util.*;

/**
 * Represents a physical Store where you can go and buy
 * products and attend events.
 */
public class Store {
    @Getter
    private final String name;
    private final Set<Product> itemsInStock = new HashSet<>();

    public Store(String name) {
        this.name = name;
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

    @SuppressWarnings("unused")
    public void saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}
