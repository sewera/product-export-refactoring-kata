package codingdojo;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
public class Price {
    private final double amount;
    private final String currencyCode;

    public String getCurrency() {
        return currencyCode;
    }

    public double getAmountInCurrency(String currencyCode) {
        if (this.currencyCode.equals(currencyCode))
            return amount;
        else {
            throw new UnsupportedOperationException("shouldn't call this from a unit test, it will do a slow db lookup");
        }
    }
}
