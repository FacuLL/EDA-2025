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
}

