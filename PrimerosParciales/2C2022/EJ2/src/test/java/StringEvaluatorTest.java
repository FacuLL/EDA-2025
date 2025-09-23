// src/test/java/ar/edu/itba/eda/StringEvaluatorTest.java
import ar.edu.itba.eda.StringEvaluator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringEvaluatorTest {

    @Test
    void testEvaluate_concat() {
        StringEvaluator evaluator = new StringEvaluator();
        assertEquals("holamundo", evaluator.evaluate("hola mundo +"));
    }

    @Test
    void testEvaluate_subtract() {
        StringEvaluator evaluator = new StringEvaluator();
        assertEquals("mundo", evaluator.evaluate("holamundo hola -"));
    }

    @Test
    void testEvaluate_delete() {
        StringEvaluator evaluator = new StringEvaluator();
        assertEquals("hmund", evaluator.evaluate("holamundo ola /"));
    }

    @Test
    void testEvaluate_intersperse() {
        StringEvaluator evaluator = new StringEvaluator();
        assertEquals("hmoollaa", evaluator.evaluate("hola mola *"));
    }

    @Test
    void testEvaluate_specialIntersperse() {
        StringEvaluator evaluator = new StringEvaluator();
        assertEquals("ahahoaholahola", evaluator.evaluate("hola a ^"));
    }

    @Test
    void testEvaluate_missingOperand() {
        StringEvaluator evaluator = new StringEvaluator();
        Exception ex = assertThrows(RuntimeException.class, () -> evaluator.evaluate("+"));
        assertEquals("Falta operando", ex.getMessage());
    }

    @Test
    void testEvaluate_missingOperator() {
        StringEvaluator evaluator = new StringEvaluator();
        Exception ex = assertThrows(RuntimeException.class, () -> evaluator.evaluate("hola mundo"));
        assertEquals("Falta operador", ex.getMessage());
    }
}

