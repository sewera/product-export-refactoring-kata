package codingdojo;

import java.util.*;

public class TaxCalculator {
    private static final Date TAX_CHANGE = DateUtil.fromIsoDate("2018-01-01T00:00Z");

    public static double calculateAddedTax(Collection<Order> orders) {
        double tax = 0.0;
        for (Order order : orders) {
            tax += orderTaxFor(order);

            for (Product product : order.products())
                tax += product.getTax();
        }

        return tax;
    }

    private static double orderTaxFor(Order order) {
        return order.date().before(TAX_CHANGE) ? 10.0 : 20.0;
    }
}
