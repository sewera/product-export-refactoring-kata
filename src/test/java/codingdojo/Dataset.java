package codingdojo;

import org.xmlunit.builder.*;

import javax.xml.transform.*;

public final class Dataset {
    private static final String TEST_XML_DIRECTORY = "src/test/resources/xml/";

    private static final Product PRODUCT = new Product("Cherry Bloom", "LIPSTICK01", 30, new Price(14.99D, "USD"));
    private static final Store STORE = new Store("Nordstan", "4189", new Product[]{PRODUCT});
    private static final StoreEvent STORE_EVENT = new StoreEvent("Makeover", "EVENT02", STORE, new Price(149.99D, "USD"));
    private static final Order ORDER_WITH_STORE_EVENT = new Order("1234", DateUtil.fromIsoDate("2018-09-01T00:00Z"),
            STORE, new Product[]{STORE_EVENT});
    private static final Order ORDER_WITH_REGULAR_PRODUCT = new Order("123", DateUtil.fromIsoDate("2017-09-01T00:00Z"), STORE, new Product[]{PRODUCT});

    public Product exampleProduct() {
        return PRODUCT;
    }

    public Store exampleStore() {
        return STORE;
    }

    public StoreEvent exampleStoreEvent() {
        return STORE_EVENT;
    }

    public Order exampleOrderWithStoreEvent() {
        return ORDER_WITH_STORE_EVENT;
    }

    public Order exampleOrderWithRegularProduct() {
        return ORDER_WITH_REGULAR_PRODUCT;
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
