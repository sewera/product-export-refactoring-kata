package codingdojo.domain;

import codingdojo.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static codingdojo.Dataset.DATE_AFTER_TAX_CHANGE;
import static codingdojo.Dataset.DATE_BEFORE_TAX_CHANGE;
import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {
    private static final boolean REGULAR_PRODUCT = false;
    private static final boolean STORE_EVENT = true;

    @Test
    void testTax_WithRegularProductBeforeTaxChange() {
        testTaxWithSingleProductForDate(REGULAR_PRODUCT, DATE_BEFORE_TAX_CHANGE, 27.5);
    }

    @Test
    void testTax_WithStoreEventBeforeTaxChange() {
        testTaxWithSingleProductForDate(STORE_EVENT, DATE_BEFORE_TAX_CHANGE, 35.0);
    }

    @Test
    void testTax_WithRegularProductAfterTaxChange() {
        testTaxWithSingleProductForDate(REGULAR_PRODUCT, DATE_AFTER_TAX_CHANGE, 37.5);
    }

    @Test
    void testTax_WithStoreEventAfterTaxChange() {
        testTaxWithSingleProductForDate(STORE_EVENT, DATE_AFTER_TAX_CHANGE, 45.0);
    }

    private void testTaxWithSingleProductForDate(boolean isEvent, Date date, double expectedTaxFor100Price) {
        // given
        var price = 100.00;
        var order = Dataset.Orders.builder()
                .withPrice(price)
                .withEvent(isEvent)
                .withDate(date)
                .build()
                .createOrder();

        // when
        var actual = order.taxInDollars();

        // then
        assertThat(actual).isEqualTo(expectedTaxFor100Price);
    }
}