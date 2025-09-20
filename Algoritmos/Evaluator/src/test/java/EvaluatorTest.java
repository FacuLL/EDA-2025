import ar.edu.itba.eda.EvaluatorInFijaBasicOperator;
import ar.edu.itba.eda.EvaluatorPostFija;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EvaluatorTest {

    @Test
    void testEvaluatorInFijaSimpleAddition() {
        String input = "3 + 4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        EvaluatorInFijaBasicOperator evaluator = new EvaluatorInFijaBasicOperator();
        assertEquals(7.0, evaluator.evaluate(), 1e-9);
    }

    @Test
    void testEvaluatorInFijaWithParenthesesAndPrecedence() {
        String input = "( 2 + 3 ) * 4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        EvaluatorInFijaBasicOperator evaluator = new EvaluatorInFijaBasicOperator();
        assertEquals(20.0, evaluator.evaluate(), 1e-9);
    }

    @Test
    void testEvaluatorInFijaWithVariables() {
        String input = "v1 * ( v3 + 2 )\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Map<String, Double> vars = new HashMap<>();
        vars.put("v1", 3.0);
        vars.put("v3", 5.0);

        EvaluatorInFijaBasicOperator evaluator = new EvaluatorInFijaBasicOperator(vars);
        assertEquals(21.0, evaluator.evaluate(), 1e-9);
    }

    @Test
    void testEvaluatorInFijaMissingOperandThrows() {
        String input = "5 +\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        EvaluatorInFijaBasicOperator evaluator = new EvaluatorInFijaBasicOperator();
        assertThrows(RuntimeException.class, evaluator::evaluate);
    }

    @Test
    void testEvaluatorPostFijaSimpleAddition() {
        String input = "3 4 +\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        EvaluatorPostFija evaluator = new EvaluatorPostFija();
        assertEquals(7.0, evaluator.evaluate(), 1e-9);
    }

    @Test
    void testEvaluatorPostFijaComplexExpression() {
        // Expresión infija: (5 + 3) * 2  → postfija: 5 3 + 2 *
        String input = "5 3 + 2 *\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        EvaluatorPostFija evaluator = new EvaluatorPostFija();
        assertEquals(16.0, evaluator.evaluate(), 1e-9);
    }

    @Test
    void testEvaluatorPostFijaInvalidExpressionThrows() {
        // Falta un operando
        String input = "5 +\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        EvaluatorPostFija evaluator = new EvaluatorPostFija();
        assertThrows(RuntimeException.class, evaluator::evaluate);
    }
}
