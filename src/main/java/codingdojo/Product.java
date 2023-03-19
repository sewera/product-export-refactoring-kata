package codingdojo;

import lombok.*;

import static codingdojo.Price.Currency.USD;

@Getter
@ToString
@AllArgsConstructor
public class Product {
    protected final String name;
    protected final String id;
    protected final int weight;
    protected final Price price;

    public double getPriceInCurrency(Price.Currency currency) {
        return price.getAmountInCurrency(currency);
    }

    public double getTaxRate() {
        return 0.175;
    }

    public double getTax() {
        return getPriceInCurrency(USD) * getTaxRate();
    }

    public boolean isEvent() {
        return false;
    }

    @SuppressWarnings("unused")
    public void saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}
