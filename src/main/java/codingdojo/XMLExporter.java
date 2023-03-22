package codingdojo;

import codingdojo.domain.*;

import java.util.*;

public class XMLExporter {
    public static String exportFull(Account account) {
        var xml = new StringBuilder();
        writeXmlHeader(xml);
        account.writeFullXml(xml);
        return xml.toString();
    }

    public static String exportTaxDetails(Account account) {
        var xml = new StringBuilder();
        writeXmlHeader(xml);
        account.writeTaxDetailsXml(xml);
        return xml.toString();
    }

    public static String exportStore(Store store) {
        var xml = new StringBuilder();
        writeXmlHeader(xml);
        store.writeStockXml(xml);
        return xml.toString();
    }

    @SuppressWarnings("unused") // public interface
    public static String exportHistory(Account account) {
        var now = new Date();
        return exportHistory(account, now);
    }

    static String exportHistory(Account account, Date dateOfCreation) {
        var xml = new StringBuilder();
        writeXmlHeader(xml);
        account.writeHistoryXml(xml, dateOfCreation);
        return xml.toString();
    }

    /**
     * @deprecated use {@link XMLExporter#exportFull(Account)} instead
     */
    @Deprecated
    @SuppressWarnings("unused") // public interface
    public static String exportFull(Collection<Order> orders) {
        return exportFull(Account.of(orders));
    }

    /**
     * @deprecated use {@link XMLExporter#exportTaxDetails(Account)} instead
     */
    @Deprecated
    @SuppressWarnings("unused") // public interface
    public static String exportTaxDetails(Collection<Order> orders) {
        return exportTaxDetails(Account.of(orders));
    }

    /**
     * @deprecated use {@link XMLExporter#exportHistory(Account)} instead
     */
    @Deprecated
    @SuppressWarnings("unused") // public interface
    public static String exportHistory(Collection<Order> orders) {
        return exportHistory(Account.of(orders));
    }

    private static void writeXmlHeader(StringBuilder xml) {
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    }
}
