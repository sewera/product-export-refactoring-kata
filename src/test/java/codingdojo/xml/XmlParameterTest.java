package codingdojo.xml;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

class XmlParameterTest {
    @Test
    void testToString() {
        // given
        var tested = XmlParameter.of("name", "value");
        var expected = " name='value'";

        // when
        var actual = tested.toString();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testToString_EmptyValue() {
        // given
        var tested = XmlParameter.of("name", "");
        var expected = " name=''";

        // when
        var actual = tested.toString();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}