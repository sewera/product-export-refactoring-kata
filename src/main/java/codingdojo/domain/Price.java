package codingdojo.domain;

import codingdojo.xml.*;

public record Price(double amount, Currency currency) {
    public void writeFullXml(StringBuilder xml) {
        xml.append(fullXml().toString());
    }

    public XmlTag fullXml() {
        return XmlTag.builder()
                .withName("price")
                .withParameter(XmlParameter.of("currency", currency.toString()))
                .withValue(String.valueOf(amount))
                .build();
    }

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
