package codingdojo;

import codingdojo.domain.*;
import lombok.*;
import org.xmlunit.builder.*;

import javax.xml.transform.*;
import java.util.*;

import static java.util.Collections.emptyList;

public final class Dataset {
    public static final Date DATE_AFTER_TAX_CHANGE = DateUtil.fromIsoDate("2018-09-01T00:00Z");
    public static final Date DATE_BEFORE_TAX_CHANGE = DateUtil.fromIsoDate("2017-09-01T00:00Z");
    private static final String TEST_XML_DIRECTORY = "src/test/resources/xml/";

    public Store exampleStoreWithStoreEvent() {
        var store = createStore();
        store.addStockedItems(storeEventForStoreWithPrice(store, 149.99));
        return store;
    }

    public Store exampleStoreWithSingleProduct() {
        var store = createStore();
        store.addStockedItems(productWithPrice(14.99));
        return store;
    }

    public Account exampleAccountWithStoreEvent() {
        var order = Orders.builder()
                .withPrice(149.99)
                .withEvent(true)
                .withDate(DATE_AFTER_TAX_CHANGE)
                .build()
                .createOrder();
        return Account.of(List.of(order));
    }

    public Account exampleAccountWithRegularProduct() {
        var order = Orders.builder()
                .withPrice(14.99)
                .withDate(DATE_BEFORE_TAX_CHANGE)
                .build()
                .createOrder();
        return Account.of(List.of(order));
    }

    @Builder(setterPrefix = "with")
    public record Orders(double price, boolean event, Date date) {
        public Order createOrder() {
            var id = "123";
            var store = createStore();
            var product = event ?
                    storeEventForStoreWithPrice(store, price) :
                    productWithPrice(price);
            store.addStockedItems(product);
            return new Order(id, date, List.of(product));
        }
    }

    private static Store createStore() {
        return new Store("Example Store", "111", emptyList());
    }

    private static StoreEvent storeEventForStoreWithPrice(Store store, double price) {
        return new StoreEvent("Store Event Two", "EVENT02", store, Money.dollars(price));
    }

    private static Product productWithPrice(double price) {
        return new RegularProduct("Product One", "PRODUCT01", Money.dollars(price), 1);
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

    public Source noOrdersReference() {
        return fromFileInXmlDir("noOrders.xml");
    }

    private Source fromFileInXmlDir(String filename) {
        return Input.fromFile(TEST_XML_DIRECTORY + filename).build();
    }
}
