package ar.edu.itba.eda;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ExpTreeTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testEvalSimple() {
        ExpTree tree = new ExpTree("( 1 + 2 )");
        assertEquals(3.0, tree.eval(), 1e-9);
    }

    @Test
    void testEvalComplex() {
        ExpTree tree = new ExpTree("( ( 2 + 3.5 ) * -10 )");
        assertEquals(-55.0, tree.eval(), 1e-9);
    }

    @Test
    void testEvalPower() {
        ExpTree tree = new ExpTree("( 2 ^ 3 )");
        assertEquals(8.0, tree.eval(), 1e-9);
    }

    @Test
    void testTraversalsOutput() {
        ExpTree tree = new ExpTree("( ( 1 + 2 ) * 3 )");

        tree.preorder();
        String preorder = outContent.toString();
        assertEquals("* + 1 2 3 \n", preorder);
        outContent.reset();

        tree.inorder();
        String inorder = outContent.toString();
        assertEquals("1 + 2 * 3 \n", inorder);
        outContent.reset();

        tree.postorder();
        String postorder = outContent.toString();
        assertEquals("1 2 + 3 * \n", postorder);
    }

    @Test
    void testInvalidOperatorThrows() {
        assertThrows(RuntimeException.class, () -> new ExpTree("( 1 ? 2 )"));
    }

    @Test
    void testBadExpressionExtraTokensThrows() {
        assertThrows(RuntimeException.class, () -> new ExpTree("( 1 + 2 ) extra"));
    }

    @Test
    void testMissingOperandThrows() {
        assertThrows(RuntimeException.class, () -> new ExpTree("( + 2 )"));
    }
}

