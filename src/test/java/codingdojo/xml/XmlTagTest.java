package codingdojo.xml;

import org.junit.jupiter.api.*;
import org.xmlunit.builder.*;

import static org.xmlunit.assertj3.XmlAssert.assertThat;

class XmlTagTest {
    @Test
    void testTagWithOneParameter() {
        // given
        var tested = XmlTag.builder()
                .withName("name")
                .withParameter(XmlParameter.of("parameter", "value"))
                .build();
        var expected = Input.fromString("<name parameter='value' />");

        // when
        var actual = tested.toString();

        // then
        assertThat(actual).and(expected).ignoreWhitespace().areIdentical();
    }

    @Test
    void testTagWithMultipleParameters() {
        // given
        var tested = XmlTag.builder()
                .withName("name")
                .withParameter(XmlParameter.of("first", "value 1"))
                .withParameter(XmlParameter.of("second", "value 2"))
                .build();
        var expected = Input.fromString("<name first='value 1' second='value 2' />");

        // when
        var actual = tested.toString();

        // then
        assertThat(actual).and(expected).ignoreWhitespace().areIdentical();
    }

    @Test
    void testTagWithValue() {
        // given
        var tested = XmlTag.builder()
                .withName("name")
                .withValue("value")
                .build();
        var expected = Input.fromString("<name>value</name>");

        // when
        var actual = tested.toString();

        // then
        assertThat(actual).and(expected).ignoreWhitespace().areIdentical();
    }

    @Test
    void testTagWithOneChild() {
        // given
        var tested = XmlTag.builder()
                .withName("root")
                .withChild(XmlTag.builder()
                        .withName("child")
                        .build())
                .build();
        var expected = Input.fromString("<root><child /></root>");

        // when
        var actual = tested.toString();

        // then
        assertThat(actual).and(expected).ignoreWhitespace().areIdentical();
    }

    @Test
    void testTagWithValueAndChildrenAndParameters() {
        // given
        var tested = XmlTag.builder()
                .withName("root")
                .withValue("value")
                .withParameter(XmlParameter.of("zero", "0"))
                .withChild(XmlTag.builder()
                        .withName("firstChild")
                        .withParameter(XmlParameter.of("first", "1"))
                        .withChild(XmlTag.builder()
                                .withName("secondChild")
                                .withParameter(XmlParameter.of("second", "2"))
                                .build())
                        .build())
                .build();
        var expected = Input.fromString("<root zero='0'><firstChild first='1'><secondChild second='2' /></firstChild>value</root>");

        // when
        var actual = tested.toString();

        // then
        assertThat(actual).and(expected).ignoreWhitespace().areIdentical();
    }

    @Test
    void testEmptyTag() {
        // given
        var tested = XmlTag.empty("name");
        var expected = Input.fromString("<name />").build();

        // when
        var actual = tested.toString();

        // then
        assertThat(actual).and(expected).ignoreWhitespace().areIdentical();
    }
}
