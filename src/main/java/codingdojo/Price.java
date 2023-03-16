package codingdojo;

public record Price(double amount, String currencyCode) {
    public double getAmountInCurrency(String currencyCode) {
        if (this.currencyCode.equals(currencyCode))
            return amount;
        else {
            throw new UnsupportedOperationException("shouldn't call this from a unit test, it will do a slow db lookup");
        }
    }
}
