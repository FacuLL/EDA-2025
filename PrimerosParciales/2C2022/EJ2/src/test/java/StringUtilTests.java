import ar.edu.itba.eda.StringUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilTests {

    @Test
    void testIndexOf_found() {
        assertEquals(2, StringUtils.indexOf("la", "hola"));
        assertEquals(0, StringUtils.indexOf("ho", "hola"));
        assertEquals(1, StringUtils.indexOf("ol", "hola"));
        assertEquals(3, StringUtils.indexOf("a", "hola"));
        assertEquals(0, StringUtils.indexOf("hola", "hola"));
    }

    @Test
    void testIndexOf_patternWithPrefixSuffixRepetition() {
        // Patrón con prefijo y sufijo repetido
        assertEquals(2, StringUtils.indexOf("ababc", "abababc"));
        assertEquals(0, StringUtils.indexOf("abab", "abababab"));
        assertEquals(1, StringUtils.indexOf("aaab", "caaabaaab"));
    }

    @Test
    void testIndexOf_multipleMatches() {
        // Patrón aparece varias veces, debe devolver la primera coincidencia
        assertEquals(0, StringUtils.indexOf("aba", "ababaaba"));
        assertEquals(2, StringUtils.indexOf("abcab", "xxabcabcab"));
    }

    @Test
    void testIndexOf_notFound() {
        assertEquals(-1, StringUtils.indexOf("z", "hola"));
    }

    @Test
    void testIndexOf_emptyString() {
        assertEquals(0, StringUtils.indexOf("", "hola"));
    }

    @Test
    void testIndexOf_nullSource() {
        assertThrows(NullPointerException.class, () -> StringUtils.indexOf(null, "a"));
    }

    @Test
    void testIndexOf_nullTarget() {
        assertThrows(NullPointerException.class, () -> StringUtils.indexOf("hola", null));
    }
}
