package codingdojo;

import java.util.*;

public class TaxCalculator {
    public static double calculateAddedTaxInDollars(Collection<Order> orders) {
        return orders.stream()
                .mapToDouble(Order::getTaxInDollars)
                .sum();
    }
}
