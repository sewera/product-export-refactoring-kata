package codingdojo;

import lombok.*;

import java.util.function.*;

import static codingdojo.Price.Currency.USD;

@Getter
@ToString
@AllArgsConstructor
public class Product {
    protected final String name;
    protected final String id;
    protected final int weight;
    protected final Price price;

    public void writeFullXml(StringBuilder xml) {
        writeDetailedXml(xml, this::writeDetailsForFullXml);
    }

    protected void writeDetailedXml(StringBuilder xml, Consumer<StringBuilder> details) {
        xml.append("<product");
        xml.append(" id='");
        xml.append(id);
        xml.append("'");
        details.accept(xml);
        xml.append(">");
        price.writeFullXml(xml);
        xml.append(name);
        xml.append("</product>");
    }

    protected void writeDetailsForFullXml(StringBuilder xml) {
        xml.append(" weight='");
        xml.append(weight);
        xml.append("'");
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
