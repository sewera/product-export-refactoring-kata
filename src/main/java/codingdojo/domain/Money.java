package codingdojo.domain;

import codingdojo.xml.*;

import java.text.*;

public record Money(double amount, Currency currency) {
    public XmlTag fullXml(String tagName) {
        return XmlTag.builder()
                .withName(tagName)
                .withParameter(XmlParameter.of("currency", currency.toString()))
                .withValue(plainAmount())
                .build();
    }

    public String plainAmount() {
        var formatter = new DecimalFormat("#0.00");
        return formatter.format(amount);
    }

    public static Money dollars(double amount) {
        return new Money(amount, Currency.USD);
    }

    public static Money sum(Money a, Money b) {
        if (!a.currency.equals(b.currency))
            throw new UnsupportedOperationException("shouldn't call this from a unit test, it will do a slow db lookup");
        return new Money(a.amount + b.amount, a.currency);
    }

    public Money timesRate(double rate) {
        return new Money(amount * rate, currency);
    }

    public double getAmountInCurrency(Currency currency) {
        if (!this.currency.equals(currency))
            throw new UnsupportedOperationException("shouldn't call this from a unit test, it will do a slow db lookup");
        return amount;
    }

    public enum Currency {
        USD
    }
}
