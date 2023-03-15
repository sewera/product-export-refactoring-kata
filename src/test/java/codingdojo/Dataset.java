package codingdojo;

import org.xmlunit.builder.*;

import javax.xml.transform.*;
import java.util.*;

public final class Dataset {
    private static final String TEST_XML_DIRECTORY = "src/test/resources/xml/";

    public Product exampleProduct() {
        return new Product("Cherry Bloom", "LIPSTICK01", 30, new Price(14.99D, "USD"));
    }

    public Store exampleStore() {
        return new Store("Nordstan", "4189", new Product[]{exampleProduct()});
    }

    public StoreEvent exampleStoreEvent() {
        return new StoreEvent("Makeover", "EVENT02", exampleStore(), new Price(149.99D, "USD"));
    }

    public List<Order> exampleOrderListWithStoreEvent() {
        var order = new Order("1234", DateUtil.fromIsoDate("2018-09-01T00:00Z"),
                new Store("Nordstan", "4189", new Product[]{exampleProduct()}), new Product[]{exampleStoreEvent()});
        return List.of(order);
    }

    public List<Order> exampleOrderListWithRegularProduct() {
        var order = new Order("123", DateUtil.fromIsoDate("2017-09-01T00:00Z"), new Store("Nordstan", "4189", new Product[]{new Product("Cherry Bloom", "LIPSTICK01", 30, new Price(14.99D, "USD"))}), new Product[]{new Product("Cherry Bloom", "LIPSTICK01", 30, new Price(14.99D, "USD"))});
        return List.of(order);
    }

    public Source allOrdersReference() {
        return fromFileInXmlDir("allOrders.xml");
    }

    public Source historyReference() {
        return fromFileInXmlDir("history.xml");
    }

    public Source storeReference() {
        return fromFileInXmlDir("store.xml");
    }

    public Source taxDetailsWithStoreEventReference() {
        return fromFileInXmlDir("taxDetails_storeEvent.xml");
    }

    public Source taxDetailsWithRegularProductReference() {
        return fromFileInXmlDir("taxDetails_regularProduct.xml");
    }

    private Source fromFileInXmlDir(String filename) {
        return Input.fromFile(TEST_XML_DIRECTORY + filename).build();
    }
}
