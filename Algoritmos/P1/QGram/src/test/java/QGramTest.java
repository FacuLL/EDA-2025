import ar.edu.itba.eda.Qgram;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QGramTest {

    @Test
    void testCompleteWordOrder2() {
        Qgram qgram = new Qgram(2);
        String result = qgram.completeWord("casa");
        assertEquals("#casa#", result);
    }

    @Test
    void testCompleteWordOrder3() {
        Qgram qgram = new Qgram(3);
        String result = qgram.completeWord("hola");
        assertEquals("##hola##", result);
    }

    @Test
    void testCompleteWordEmpty() {
        Qgram qgram = new Qgram(2);
        String result = qgram.completeWord("");
        assertEquals("##", result);
    }

    @Test
    void testGetTokensSimple() {
        Qgram qgram = new Qgram(2);
        Map<String, Integer> tokens = qgram.getTokens("casa");
        // Espera algunos bigramas comunes
        assertTrue(tokens.containsKey("#c"));
        assertTrue(tokens.containsKey("ca"));
        assertTrue(tokens.containsKey("as"));
        assertTrue(tokens.containsKey("sa"));
        assertTrue(tokens.containsKey("a#"));
    }

    @Test
    void testGetTokensOrder3() {
        Qgram qgram = new Qgram(3);
        Map<String, Integer> tokens = qgram.getTokens("hola");
        assertTrue(tokens.containsKey("##h"));
        assertTrue(tokens.containsKey("#ho"));
        assertTrue(tokens.containsKey("hol"));
        assertTrue(tokens.containsKey("ola"));
        assertTrue(tokens.containsKey("la#"));
        assertTrue(tokens.containsKey("a##"));
    }

    @Test
    void testCalculateDistanceIdentical() {
        Qgram qgram = new Qgram(2);
        double dist = qgram.calculateDistance("casa", "casa");
        assertEquals(1.0, dist, 1e-6);
    }

    @Test
    void testCalculateDistanceDifferent() {
        Qgram qgram = new Qgram(2);
        double dist = qgram.calculateDistance("casa", "perro");
        assertTrue(dist < 1.0 && dist >= 0.0);
    }

    @Test
    void testCalculateDistanceEmpty() {
        Qgram qgram = new Qgram(2);
        double dist = qgram.calculateDistance("", "");
        assertEquals(1.0, dist, 1e-6);
    }

    @Test
    void testPrintTokensNoException() {
        Qgram qgram = new Qgram(2);
        assertDoesNotThrow(() -> qgram.printTokens("casa"));
    }
}
