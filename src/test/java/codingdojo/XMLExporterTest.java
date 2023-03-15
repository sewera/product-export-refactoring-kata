package codingdojo;

import org.junit.jupiter.api.*;
import org.xmlunit.builder.*;

import java.util.*;

import static org.xmlunit.assertj3.XmlAssert.assertThat;


class XMLExporterTest {
    private static final String TEST_XML_DIRECTORY = "src/test/resources/xml/";
    private static final Dataset data = new Dataset();

    private static final Product PRODUCT = new Product("Cherry Bloom", "LIPSTICK01", 30, new Price(14.99D, "USD"));
    private static final Store STORE = new Store("Nordstan", "4189", new Product[]{PRODUCT});
    private static final StoreEvent STORE_EVENT = new StoreEvent("Makeover", "EVENT02", STORE, new Price(149.99D, "USD"));
    private static final Order ORDER_WITH_STORE_EVENT = new Order("1234", DateUtil.fromIsoDate("2018-09-01T00:00Z"),
            STORE, new Product[]{STORE_EVENT});
    private static final Order ORDER_WITH_REGULAR_PRODUCT = new Order("123", DateUtil.fromIsoDate("2017-09-01T00:00Z"), STORE, new Product[]{PRODUCT});

    @Test
    void testFullExport() {
        // given
        var expected = data.allOrdersReference();

        // when
        var actual = XMLExporter.exportFull(List.of(ORDER_WITH_STORE_EVENT));

        // then
        assertThat(actual).and(expected).ignoreWhitespace().areIdentical();
    }

    @Test
    void testTaxDetailsExportForStoreEvent() {
        // given
        var expectedXmlFile = "taxDetails_storeEvent.xml";

        // when
        var xml = XMLExporter.exportTaxDetails(List.of(ORDER_WITH_STORE_EVENT));

        // then
        assertXmlAgainstFile(xml, expectedXmlFile);
    }

    @Test
    void testTaxDetailsExportForRegularProduct() {
        // given
        var expectedXmlFile = "taxDetails_regularProduct.xml";

        // when
        var xml = XMLExporter.exportTaxDetails(List.of(ORDER_WITH_REGULAR_PRODUCT));

        // then
        assertXmlAgainstFile(xml, expectedXmlFile);
    }

    @Test
    void testStoreExport() {
        // given
        var expectedXmlFile = "store.xml";

        // when
        var xml = XMLExporter.exportStore(STORE);

        // then
        assertXmlAgainstFile(xml, expectedXmlFile);
    }

    @Test
    void testHistoryExport() {
        // given
        var expectedXmlFile = "history.xml";

        // when
        var xml = XMLExporter.exportHistory(List.of(ORDER_WITH_STORE_EVENT), DateUtil.fromIsoDate("2023-03-31T12:35Z"));

        // then
        assertXmlAgainstFile(xml, expectedXmlFile);
    }

    private static void assertXmlAgainstFile(String actualXml, String expectedXmlFile) {
        var expected = Input.fromFile(TEST_XML_DIRECTORY + expectedXmlFile).build();
        var actual = Input.fromString(actualXml).build();

        assertThat(actual).and(expected).ignoreWhitespace().areIdentical();
    }
}

