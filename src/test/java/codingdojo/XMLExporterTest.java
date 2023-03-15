package codingdojo;

import org.junit.jupiter.api.*;

import javax.xml.transform.*;

import static org.xmlunit.assertj3.XmlAssert.assertThat;


class XMLExporterTest {
    private static final Dataset data = new Dataset();

    @Test
    void testFullExport() {
        // given
        var orders = data.exampleOrderListWithStoreEvent();
        var expected = data.allOrdersReference();

        // when
        var actual = XMLExporter.exportFull(orders);

        // then
        assertXml(actual, expected);
    }

    @Test
    void testTaxDetailsExportForStoreEvent() {
        // given
        var orders = data.exampleOrderListWithStoreEvent();
        var expected = data.taxDetailsWithStoreEventReference();

        // when
        var actual = XMLExporter.exportTaxDetails(orders);

        // then
        assertXml(actual, expected);
    }

    @Test
    void testTaxDetailsExportForRegularProduct() {
        // given
        var orders = data.exampleOrderListWithRegularProduct();
        var expected = data.taxDetailsWithRegularProductReference();

        // when
        var actual = XMLExporter.exportTaxDetails(orders);

        // then
        assertXml(actual, expected);
    }

    @Test
    void testStoreExportForStoreWithSingleProduct() {
        // given
        var store = data.exampleStoreWithSingleProduct();
        var expected = data.storeWithSingleProductReference();

        // when
        var actual = XMLExporter.exportStore(store);

        // then
        assertXml(actual, expected);
    }

    @Test
    void testStoreExportForStoreWithStoreEvent() {
        // given
        var store = data.exampleStoreWithStoreEvent();
        var expected = data.storeWithStoreEventReference();

        // when
        var actual = XMLExporter.exportStore(store);

        // then
        assertXml(actual, expected);
    }

    @Test
    void testHistoryExport() {
        // given
        var orders = data.exampleOrderListWithStoreEvent();
        var date = DateUtil.fromIsoDate("2023-03-31T12:35Z");
        var expected = data.historyReference();

        // when
        var actual = XMLExporter.exportHistory(orders, date);

        // then
        assertXml(actual, expected);
    }

    private static void assertXml(String actual, Source expected) {
        assertThat(actual).and(expected).ignoreWhitespace().areIdentical();
    }
}

