package codingdojo;

import lombok.*;

import java.util.*;

@AllArgsConstructor
public class Ledger {
    private final List<Order> orders;

    public double getTaxInDollars() {
        return orders.stream().mapToDouble(Order::getTaxInDollars).sum();
    }
}
