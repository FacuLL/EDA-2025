import ar.edu.itba.eda.DelegatedSortedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DelegatedSortedListTest {

    private DelegatedSortedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new DelegatedSortedList<>();
    }

    @Test
    void testInsertAndSize() {
        assertTrue(list.insert(10));
        assertTrue(list.insert(5));
        assertTrue(list.insert(20));
        assertTrue(list.insert(15));
        assertTrue(list.insert(8));
        assertEquals(5, list.size());
    }

    @Test
    void testGetMinAndMax() {
        list.insert(10);
        list.insert(5);
        list.insert(20);
        list.insert(15);
        list.insert(8);
        assertEquals(5, list.getMin());
        assertEquals(20, list.getMax());
    }

    @Test
    void testFind() {
        list.insert(10);
        list.insert(5);
        list.insert(20);
        list.insert(15);
        list.insert(8);
        assertTrue(list.find(15));
        assertFalse(list.find(7));
    }

    @Test
    void testInsertDuplicate() {
        list.insert(10);
        assertFalse(list.insert(10));
    }

    @Test
    void testRemove() {
        list.insert(10);
        list.insert(5);
        list.insert(20);
        list.insert(15);
        list.insert(8);
        assertTrue(list.remove(5));
        assertFalse(list.remove(42));
        assertEquals(4, list.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.insert(1);
        assertFalse(list.isEmpty());
    }

    @Test
    void testGetMinThrowsExceptionWhenEmpty() {
        assertThrows(NoSuchElementException.class, () -> list.getMin());
    }

    @Test
    void testGetMaxThrowsExceptionWhenEmpty() {
        assertThrows(NoSuchElementException.class, () -> list.getMax());
    }
}

