package codingdojo.xml;

public record XmlParameter(String name, String value) {
    public static XmlParameter of(String name, String value) {
        return new XmlParameter(name, value);
    }

    @Override
    public String toString() {
        return " " + name + "='" + value + "'";
    }
}
