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
        xml.append("<product");
        xml.append(" id='");
        xml.append(id);
        xml.append("'");
        writeDetailsForFullXml(xml);
        xml.append(">");
        price.writeFullXml(xml);
        xml.append(name);
        xml.append("</product>");
    }

    protected void writeDetailsForFullXml(StringBuilder xml) {
        var parameter = XmlParameter.of("weight", String.valueOf(weight));
        xml.append(parameter);
    }

    public void writeStockXml(StringBuilder xml) {
        writeFullXml(xml);
    }

    public void writeBasicXml(StringBuilder xml) {
        xml.append("<product");
        xml.append(" id='");
        xml.append(id);
        xml.append("'");
        xml.append(">");
        xml.append(name);
        xml.append("</product>");
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
