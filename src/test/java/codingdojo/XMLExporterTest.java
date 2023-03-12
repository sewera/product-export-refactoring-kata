package codingdojo;

import org.junit.jupiter.api.*;
import org.xmlunit.builder.*;

import java.util.*;

import static org.xmlunit.assertj3.XmlAssert.assertThat;


public class XMLExporterTest {

    @Test
    public void testFullExport() {
        // given
        var product = new Product("Cherry Bloom", "LIPSTICK01", 30, new Price(14.99D, "USD"));
        var store = new Store("Nordstan", "4189", new Product[]{product});
        var makeover = new StoreEvent("Makeover", "EVENT02", store, new Price(149.99D, "USD"));
        var order = new Order("1234", Util.fromIsoDate("2018-09-01T00:00Z"),
                store, new Product[]{makeover});

        // when
        var xml = XMLExporter.exportFull(List.of(order));
        var expected = Input.fromFile("src/test/resources/xml/recentOrder.xml").build();
        var actual = Input.fromString(xml).build();

        // then
        assertThat(actual).and(expected).ignoreWhitespace().areIdentical();
    }
}

