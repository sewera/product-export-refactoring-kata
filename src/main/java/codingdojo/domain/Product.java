package codingdojo.domain;

import codingdojo.xml.*;
import lombok.*;

import static codingdojo.domain.Price.Currency.USD;
import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
public abstract class Product {
    protected final String name;
    protected final String id;
    protected final Price price;

    public abstract XmlTag fullXml();

    protected XmlTag xmlWithPrice() {
        return basicXml().toBuilder()
                .withChild(price.fullXml())
                .build();
    }

    public abstract XmlTag stockXml();

    public XmlTag basicXml() {
        return XmlTag.builder()
                .withName("product")
                .withParameter(XmlParameter.of("id", id))
                .withValue(name)
                .build();
    }

    double priceInDollars() {
        return price.getAmountInCurrency(USD);
    }

    protected abstract double taxRate();

    double taxInDollars() {
        return priceInDollars() * taxRate();
    }

    @SuppressWarnings("unused")
    public void saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}
