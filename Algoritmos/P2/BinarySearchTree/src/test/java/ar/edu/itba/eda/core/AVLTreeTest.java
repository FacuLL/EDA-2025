package ar.edu.itba.eda.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    private AVLTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new AVLTree<>();
    }

    @Test
    void insertAndFind() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(6);
        assertTrue(tree.find(10));
        assertTrue(tree.find(6));
        assertFalse(tree.find(99));
    }

    @Test
    void heightAndToString() {
        for (int i = 1; i <= 7; i++) tree.insert(i);
        int h = tree.getHeight();
        assertTrue(h > 0);
        String s = tree.toString();
        // La representación debe contener algunos de los valores insertados
        assertTrue(s.contains("1"));
        assertTrue(s.contains("7"));
    }

    @Test
    void deleteLeaf() {
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        assertTrue(tree.find(5));
        tree.delete(5);
        assertFalse(tree.find(5));
        // root still present
        assertTrue(tree.find(10));
    }

    @Test
    void deleteNodeWithOneChild() {
        tree.insert(20);
        tree.insert(10);
        tree.insert(5);
        // 10 has one child (5)
        assertTrue(tree.find(10));
        tree.delete(10);
        assertFalse(tree.find(10));
        assertTrue(tree.find(5));
    }

    @Test
    void deleteNodeWithTwoChildren() {
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(10);
        tree.insert(25);
        // 20 has two children
        assertTrue(tree.find(20));
        tree.delete(20);
        assertFalse(tree.find(20));
        // children should still be present
        assertTrue(tree.find(10));
        assertTrue(tree.find(25));
    }

    @Test
    void deleteRootAndRebalance() {
        // Insert a set that forces rebalances
        int[] vals = {1,2,3,4,5,6,7,8,9};
        for (int v : vals) tree.insert(v);
        assertTrue(tree.find(1));
        assertTrue(tree.find(9));
        // delete root (which will be some value depending on rotations)
        Integer rootData = ((NodeTreeInterface<Integer>) tree.getRoot()).getData();
        assertNotNull(rootData);
        tree.delete(rootData);
        assertFalse(tree.find(rootData));
        // tree still should contain some elements
        assertTrue(tree.find(5));
    }

    @Test
    void deleteNonExistingAndNull() {
        tree.insert(1);
        tree.insert(2);
        // deleting non-existing should do nothing (no exception)
        tree.delete(99);
        assertTrue(tree.find(1));

        // null should throw
        assertThrows(IllegalArgumentException.class, () -> tree.delete(null));
    }
}
