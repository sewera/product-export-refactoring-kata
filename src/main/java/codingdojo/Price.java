package codingdojo;

public record Price(double amount, Currency currency) {

    public double getAmountInCurrency(Currency currency) {
        if (this.currency.equals(currency))
            return amount;
        else {
            throw new UnsupportedOperationException("shouldn't call this from a unit test, it will do a slow db lookup");
        }
    }

    public enum Currency {
        USD
    }
}
