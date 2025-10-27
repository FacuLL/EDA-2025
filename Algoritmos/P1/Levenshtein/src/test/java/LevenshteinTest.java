import static org.junit.jupiter.api.Assertions.*;

import ar.edu.itba.eda.Levenshtein;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LevenshteinTest {

    @Test
    @DisplayName("Min correcto")
    void minTest() {
        assertEquals(1, Levenshtein.min(1, 2, 1, 3, 4, 23));
    }

    @Test
    @DisplayName("Calculate correcto")
    void calculateTest() {
        assertEquals(1, Levenshtein.calculate("big data", "bigdata"));
        assertEquals(4, Levenshtein.calculate("big", "bigdata"));
        assertEquals(7, Levenshtein.calculate("", "bigdata"));
        assertEquals(0, Levenshtein.calculate("", ""));
        assertEquals(2, Levenshtein.calculate("bigda", "bigar"));
    }
}
