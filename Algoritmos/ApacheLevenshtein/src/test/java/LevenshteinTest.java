import static org.junit.jupiter.api.Assertions.*;

import ar.edu.itba.eda.ApacheLevenshtein;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LevenshteinTest {

    @Test
    @DisplayName("Calculate correcto")
    void calculateTest() {
        assertEquals(1, ApacheLevenshtein.calculate("big data", "bigdata"));
        assertEquals(4, ApacheLevenshtein.calculate("big", "bigdata"));
        assertEquals(7, ApacheLevenshtein.calculate("", "bigdata"));
        assertEquals(0, ApacheLevenshtein.calculate("", ""));
        assertEquals(2, ApacheLevenshtein.calculate("bigda", "bigar"));
    }
}