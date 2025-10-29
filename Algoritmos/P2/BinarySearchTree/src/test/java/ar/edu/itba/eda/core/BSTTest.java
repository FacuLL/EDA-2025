package ar.edu.itba.eda.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {

    private BST<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new BST<>();
    }

    @Test
    void inOrderTraversalIsSorted() {
        int[] vals = {50, 20, 70, 10, 30, 60, 80};
        for (int v : vals) tree.insert(v);

        tree.setTraversal(BSTreeInterface.Traversal.INORDER);
        List<Integer> out = new ArrayList<>();
        for (Integer v : tree) out.add(v);

        assertEquals(vals.length, out.size());
        for (int i = 0; i < out.size() - 1; i++) {
            assertTrue(out.get(i) <= out.get(i + 1));
        }
    }

    @Test
    void deleteRemovesElements() {
        int[] vals = {50, 20, 70, 10, 30, 60, 80};
        for (int v : vals) tree.insert(v);

        tree.delete(80);
        tree.delete(10);

        tree.setTraversal(BSTreeInterface.Traversal.INORDER);
        List<Integer> out = new ArrayList<>();
        for (Integer v : tree) out.add(v);

        assertFalse(out.contains(80));
        assertFalse(out.contains(10));
    }
}

