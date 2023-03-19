package codingdojo;

import java.util.*;

import static codingdojo.Price.Currency.USD;

public class TaxCalculator {
    private static final Date TAX_CHANGE = DateUtil.fromIsoDate("2018-01-01T00:00Z");

    public static double calculateAddedTax(Collection<Order> orders) {
        double tax = 0.0;
        for (Order order : orders) {
            if (order.date().before(TAX_CHANGE))
                tax += 10;
            else
                tax += 20;

            for (Product product : order.products())
                if (product.isEvent())
                    tax += product.getPriceInCurrency(USD) * 0.25;
                else
                    tax += product.getPriceInCurrency(USD) * 0.175;
        }

        return tax;
    }
}
