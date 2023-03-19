package codingdojo;

import org.junit.jupiter.api.*;

import java.util.*;

import static codingdojo.Dataset.DATE_AFTER_TAX_CHANGE;
import static codingdojo.Dataset.DATE_BEFORE_TAX_CHANGE;
import static org.assertj.core.api.Assertions.assertThat;

class TaxCalculatorTest {
    private static final boolean REGULAR_PRODUCT = false;
    private static final boolean STORE_EVENT = true;

    @Test
    void testTaxForOrderWithRegularProductBeforeTaxChange() {
        testTaxForOrderWithSingleProductForDate(REGULAR_PRODUCT, DATE_BEFORE_TAX_CHANGE, 27.5);
    }

    @Test
    void testTaxForOrderWithStoreEventBeforeTaxChange() {
        testTaxForOrderWithSingleProductForDate(STORE_EVENT, DATE_BEFORE_TAX_CHANGE, 35.0);
    }

    @Test
    void testTaxForOrderWithRegularProductAfterTaxChange() {
        testTaxForOrderWithSingleProductForDate(REGULAR_PRODUCT, DATE_AFTER_TAX_CHANGE, 37.5);
    }

    @Test
    void testTaxForOrderWithStoreEventAfterTaxChange() {
        testTaxForOrderWithSingleProductForDate(STORE_EVENT, DATE_AFTER_TAX_CHANGE, 45.0);
    }

    private void testTaxForOrderWithSingleProductForDate(boolean isEvent, Date date, double expectedTaxFor100Price) {
        // given
        var price = 100.00;
        var order = Dataset.Orders.builder()
                .withPrice(price)
                .withEvent(isEvent)
                .withDate(date)
                .build()
                .createOrder();

        // when
        var actual = TaxCalculator.calculateAddedTax(List.of(order));

        // then
        assertThat(actual).isEqualTo(expectedTaxFor100Price);
    }

    @Test
    void testTaxForOrderWithMultipleProducts() {
        // given
        var regularProductBeforeTaxChange = Dataset.Orders.builder()
                .withPrice(100.0)
                .withDate(DATE_BEFORE_TAX_CHANGE)
                .build()
                .createOrder();
        var regularProductAfterTaxChange = Dataset.Orders.builder()
                .withPrice(200.0)
                .withDate(DATE_AFTER_TAX_CHANGE)
                .build()
                .createOrder();
        var storeEventBeforeTaxChange = Dataset.Orders.builder()
                .withPrice(400.0)
                .withEvent(true)
                .withDate(DATE_BEFORE_TAX_CHANGE)
                .build()
                .createOrder();
        var storeEventAfterTaxChange = Dataset.Orders.builder()
                .withPrice(500.0)
                .withEvent(true)
                .withDate(DATE_AFTER_TAX_CHANGE)
                .build()
                .createOrder();
        var expected = 337.5;

        // when
        var actual = TaxCalculator.calculateAddedTax(List.of(regularProductBeforeTaxChange,
                regularProductAfterTaxChange, storeEventBeforeTaxChange, storeEventAfterTaxChange));

        // then
        assertThat(actual).isEqualTo(expected);
    }
}