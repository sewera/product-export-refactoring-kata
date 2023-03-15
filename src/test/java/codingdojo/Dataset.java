package codingdojo;

import org.xmlunit.builder.*;

import javax.xml.transform.*;
import java.util.*;

public final class Dataset {
    private static final String TEST_XML_DIRECTORY = "src/test/resources/xml/";

    public Product exampleProduct() {
        return new Product("Product One", "PRODUCT01", 1, new Price(14.99D, "USD"));
    }

    public Store exampleStoreWithSingleProduct() {
        return new Store("Example Store", "111", new Product[]{exampleProduct()});
    }

    public Store exampleStoreWithStoreEvent() {
        var store = exampleStoreWithSingleProduct();
        storeEventFor(store);
        return store;
    }

    public StoreEvent exampleStoreEvent() {
        return storeEventFor(exampleStoreWithSingleProduct());
    }

    private StoreEvent storeEventFor(Store store) {
        return new StoreEvent("Store Event Two", "EVENT02", store, new Price(149.99D, "USD"));
    }

    public List<Order> exampleOrderListWithStoreEvent() {
        var dateAfterTaxChange = DateUtil.fromIsoDate("2018-09-01T00:00Z");
        var order = new Order("1234", dateAfterTaxChange, exampleStoreWithStoreEvent(), new Product[]{exampleStoreEvent()});
        return List.of(order);
    }

    public List<Order> exampleOrderListWithRegularProduct() {
        var dateBeforeTaxChange = DateUtil.fromIsoDate("2017-09-01T00:00Z");
        var order = new Order("123", dateBeforeTaxChange, exampleStoreWithSingleProduct(), new Product[]{exampleProduct()});
        return List.of(order);
    }

    public Source allOrdersReference() {
        return fromFileInXmlDir("allOrders.xml");
    }

    public Source historyReference() {
        return fromFileInXmlDir("history.xml");
    }

    public Source storeWithSingleProductReference() {
        return fromFileInXmlDir("store_singleProduct.xml");
    }

    public Source storeWithStoreEventReference() {
        return fromFileInXmlDir("store_storeEvent.xml");
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
