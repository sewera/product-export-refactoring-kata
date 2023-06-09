package codingdojo.domain;

import codingdojo.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static codingdojo.Dataset.DATE_AFTER_TAX_CHANGE;
import static codingdojo.Dataset.DATE_BEFORE_TAX_CHANGE;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {
    @Test
    void testTax_MultipleOrders() {
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
        var orders = List.of(regularProductBeforeTaxChange,
                regularProductAfterTaxChange, storeEventBeforeTaxChange, storeEventAfterTaxChange);
        var account = Account.of(orders);

        var expected = Money.dollars(337.5);

        // when
        var actual = account.getTax();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testTax_NoOrders() {
        // given
        var ledger = Account.of(emptyList());
        var expected = Money.dollars(0.0);

        // when
        var actual = ledger.getTax();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}