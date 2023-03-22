package codingdojo.domain;

import codingdojo.xml.*;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
public abstract class Product {
    protected final String name;
    protected final String id;
    @Getter
    protected final Money price;

    public abstract XmlTag fullXml();

    protected XmlTag xmlWithPrice() {
        return basicXml().toBuilder()
                .withChild(price.fullXml("price"))
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

    protected abstract double taxRate();

    Money tax() {
        return price.timesRate(taxRate());
    }

    @SuppressWarnings("unused")
    public void saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}
