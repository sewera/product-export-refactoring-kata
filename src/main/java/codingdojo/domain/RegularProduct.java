package codingdojo.domain;

import codingdojo.xml.*;
import lombok.*;

@Getter
@ToString
public class RegularProduct extends Product {
    private final int weight;

    public RegularProduct(String name, String id, Price price, int weight) {
        super(name, id, price);
        this.weight = weight;
    }

    @Override
    public XmlTag fullXml() {
        return xmlWithPrice().toBuilder()
                .withParameter(XmlParameter.of("weight", String.valueOf(weight)))
                .build();
    }

    @Override
    public XmlTag stockXml() {
        return fullXml();
    }

    @Override
    protected double taxRate() {
        return 0.175;
    }
}
