package codingdojo;

import org.junit.jupiter.api.*;
import org.xmlunit.builder.*;

import static org.xmlunit.assertj3.XmlAssert.assertThat;


class XMLExporterTest {
    private static final String TEST_XML_DIRECTORY = "src/test/resources/xml/";
    private static final Dataset data = new Dataset();

    @Test
    void testFullExport() {
        // given
        var orders = data.exampleOrderListWithStoreEvent();
        var expected = data.allOrdersReference();

        // when
        var actual = XMLExporter.exportFull(orders);

        // then
        assertThat(actual).and(expected).ignoreWhitespace().areIdentical();
    }

    @Test
    void testTaxDetailsExportForStoreEvent() {
        // given
        var orders = data.exampleOrderListWithStoreEvent();
        var expectedXmlFile = "taxDetails_storeEvent.xml";

        // when
        var xml = XMLExporter.exportTaxDetails(orders);

        // then
        assertXmlAgainstFile(xml, expectedXmlFile);
    }

    @Test
    void testTaxDetailsExportForRegularProduct() {
        // given
        var orders = data.exampleOrderListWithRegularProduct();
        var expectedXmlFile = "taxDetails_regularProduct.xml";

        // when
        var xml = XMLExporter.exportTaxDetails(orders);

        // then
        assertXmlAgainstFile(xml, expectedXmlFile);
    }

    @Test
    void testStoreExport() {
        // given
        var store = data.exampleStore();
        var expectedXmlFile = "store.xml";

        // when
        var xml = XMLExporter.exportStore(store);

        // then
        assertXmlAgainstFile(xml, expectedXmlFile);
    }

    @Test
    void testHistoryExport() {
        // given
        var orders = data.exampleOrderListWithStoreEvent();
        var date = DateUtil.fromIsoDate("2023-03-31T12:35Z");
        var expectedXmlFile = "history.xml";

        // when
        var xml = XMLExporter.exportHistory(orders, date);

        // then
        assertXmlAgainstFile(xml, expectedXmlFile);
    }

    private static void assertXmlAgainstFile(String actualXml, String expectedXmlFile) {
        var expected = Input.fromFile(TEST_XML_DIRECTORY + expectedXmlFile).build();
        var actual = Input.fromString(actualXml).build();

        assertThat(actual).and(expected).ignoreWhitespace().areIdentical();
    }
}

