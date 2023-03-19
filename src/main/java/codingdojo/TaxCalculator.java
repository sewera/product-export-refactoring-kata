package codingdojo;

import java.util.*;

public class TaxCalculator {
    private static final Date TAX_CHANGE = DateUtil.fromIsoDate("2018-01-01T00:00Z");

    public static double calculateAddedTax(Collection<Order> orders) {
        return orders.stream()
                .map(TaxCalculator::taxForOrder)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    private static double taxForOrder(Order order) {
        var tax = initialTaxForOrder(order);

        for (var product : order.products())
            tax += product.getTax();
        return tax;
    }

    private static double initialTaxForOrder(Order order) {
        return order.date().before(TAX_CHANGE) ? 10.0 : 20.0;
    }
}
