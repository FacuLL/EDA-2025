package ar.edu.itba.eda.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BTreeTest {

    private BTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new BTree<>();
    }

    @Test
    void testAddContainsSizeValidate() {
        for (int i = 1; i <= 10; i++) tree.add(i);
        assertEquals(10, tree.size());
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(10));
        assertTrue(tree.validate());
    }

    @Test
    void testRemoveLeafAndInternal() {
        int[] vals = {10, 20, 5, 6, 12, 30, 7, 17};
        for (int v : vals) tree.add(v);
        assertTrue(tree.contains(6));
        Integer removed = tree.remove(6);
        assertEquals(6, removed);
        assertFalse(tree.contains(6));
        assertTrue(tree.validate());
    }

    @Test
    void testToCollectionIteratorAndRemove() {
        for (int i = 1; i <= 15; i++) tree.add(i);
        var col = tree.toCollection();
        List<Integer> seen = new ArrayList<>();
        Iterator<Integer> it = col.iterator();
        while (it.hasNext()) seen.add(it.next());
        assertEquals(tree.size(), seen.size());

        // remove some via collection.remove
        assertTrue(col.remove(5));
        assertFalse(col.contains(5));
        assertTrue(tree.validate());
    }
}

