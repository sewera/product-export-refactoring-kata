package codingdojo;

import org.junit.jupiter.api.*;

import java.util.*;

import static codingdojo.Dataset.DATE_AFTER_TAX_CHANGE;
import static codingdojo.Dataset.DATE_BEFORE_TAX_CHANGE;
import static org.assertj.core.api.Assertions.assertThat;

class TaxCalculatorTest {
    @Test
    void testTaxForNoOrders() {
        // given
        var expected = 0.0;

        // when
        var actual = TaxCalculator.calculateAddedTaxInDollars(List.of());

        // then
        assertThat(actual).isEqualTo(expected);
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
        var actual = TaxCalculator.calculateAddedTaxInDollars(List.of(regularProductBeforeTaxChange,
                regularProductAfterTaxChange, storeEventBeforeTaxChange, storeEventAfterTaxChange));

        // then
        assertThat(actual).isEqualTo(expected);
    }
}