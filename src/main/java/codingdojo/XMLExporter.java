package codingdojo;

import codingdojo.domain.*;

import java.util.*;

public class XMLExporter {
    public static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    public static String exportFull(Account account) {
        return XML_HEADER + account.fullXml();
    }

    public static String exportTaxDetails(Account account) {
        return XML_HEADER + account.taxDetailsXml();
    }

    public static String exportStore(Store store) {
        return XML_HEADER + store.stockXml();
    }

    @SuppressWarnings("unused") // public interface
    public static String exportHistory(Account account) {
        var now = new Date();
        return exportHistory(account, now);
    }

    static String exportHistory(Account account, Date dateOfCreation) {
        return XML_HEADER + account.historyXml(dateOfCreation);
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
}
