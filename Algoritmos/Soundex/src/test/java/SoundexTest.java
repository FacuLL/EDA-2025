import ar.edu.itba.eda.Soundex;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SoundexTest {

    @Test()
    @DisplayName("Encoding correcto")
    void encodeTest() {
        assertEquals("H400", Soundex.encode("hello"));
        assertEquals("P500", Soundex.encode("phone"));
        assertEquals("F500", Soundex.encode("foun"));
    }

    @Test
    @DisplayName("Comparations correctos")
    void comparateTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            Soundex.comparate("H12345", "H1234");
        });
        assertEquals(0.25, Soundex.comparate("H123", "H231"));
        assertEquals(0.5, Soundex.comparate("D521", "H523"));
        assertEquals(0.75, Soundex.comparate("D222", "J222"));
        assertEquals(1, Soundex.comparate("J222", "J222"));
    }
}
