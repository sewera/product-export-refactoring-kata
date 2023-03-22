package codingdojo.xml;

import lombok.*;

import java.util.*;

import static java.util.Collections.emptyList;
import static lombok.AccessLevel.PRIVATE;

@Builder(setterPrefix = "with", toBuilder = true)
@AllArgsConstructor(access = PRIVATE)
public class XmlTag {
    private final String name;
    @Builder.Default
    private final String value = "";
    @Singular
    private final List<XmlParameter> parameters;
    @Singular
    private final List<XmlTag> children;

    public static XmlTag empty(String name) {
        return new XmlTag(name, "", emptyList(), emptyList());
    }

    @Override
    public String toString() {
        var xml = new StringBuilder();
        xml.append("<");
        xml.append(name);
        parameters.forEach(parameter -> xml.append(parameter.toString()));
        xml.append(">");
        children.forEach(child -> xml.append(child.toString()));
        xml.append(value);
        xml.append("</");
        xml.append(name);
        xml.append(">");

        return xml.toString();
    }
}
