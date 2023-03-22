package codingdojo.domain;

import codingdojo.xml.*;
import lombok.*;

import static codingdojo.domain.Price.Currency.USD;

@Getter
@ToString
@AllArgsConstructor
public class Product {
    protected final String name;
    protected final String id;
    protected final int weight;
    protected final Price price;

    public void writeFullXml(StringBuilder xml) {
        xml.append(fullXml());
    }

    public XmlTag fullXml() {
        return XmlTag.builder()
                .withName("product")
                .withParameter(XmlParameter.of("id", id))
                .withParameter(detailsForFullXml())
                .withChild(price.fullXml())
                .withValue(name)
                .build();
    }

    protected XmlParameter detailsForFullXml() {
        return XmlParameter.of("weight", String.valueOf(weight));
    }

    public void writeStockXml(StringBuilder xml) {
        xml.append(stockXml());
    }

    public XmlTag stockXml() {
        return fullXml();
    }

    public void writeBasicXml(StringBuilder xml) {
        xml.append(basicXml());
    }

    private XmlTag basicXml() {
        return XmlTag.builder()
                .withName("product")
                .withParameter(XmlParameter.of("id", id))
                .withValue(name)
                .build();
    }

    double getPriceInDollars() {
        return price.getAmountInCurrency(USD);
    }

    protected double getTaxRate() {
        return 0.175;
    }

    double getTaxInDollars() {
        return getPriceInDollars() * getTaxRate();
    }

    @SuppressWarnings("unused")
    public void saveToDatabase() {
        throw new UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test");
    }
}
