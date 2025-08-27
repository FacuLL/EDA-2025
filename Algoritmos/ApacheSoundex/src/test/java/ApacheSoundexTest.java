import ar.edu.itba.eda.ApacheSoundex;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ApacheSoundexTest {

    @Test()
    @DisplayName("Encoding correcto")
    void encodeTest() {
        assertEquals("H400", ApacheSoundex.encode("hello"));
        assertEquals("P500", ApacheSoundex.encode("phone"));
        assertEquals("F500", ApacheSoundex.encode("foun"));
    }

    @Test
    @DisplayName("Comparations correctos")
    void comparateTest() {
        assertEquals(2, ApacheSoundex.comparate("hello", "phone"));
        assertEquals(3, ApacheSoundex.comparate("phone", "foun"));
    }
}

