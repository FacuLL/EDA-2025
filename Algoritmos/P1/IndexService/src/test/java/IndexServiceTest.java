import ar.edu.itba.eda.IndexServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class IndexServiceTest {

    private IndexServiceImpl<Integer> indexService;

    @BeforeEach
    void setUp() {
        indexService = new IndexServiceImpl<>(Integer.class);
    }

    @Test
    void testInitializeAndSearch() {
        Integer[] arr = {3, 1, 2};
        indexService.initialize(arr);
        assertTrue(indexService.search(1));
        assertTrue(indexService.search(2));
        assertTrue(indexService.search(3));
        assertFalse(indexService.search(4));
    }

    @Test
    void testInsertAndSearch() {
        indexService.insert(5);
        indexService.insert(2);
        indexService.insert(8);
        assertTrue(indexService.search(2));
        assertTrue(indexService.search(5));
        assertTrue(indexService.search(8));
        assertFalse(indexService.search(1));
    }

    @Test
    void testDelete() {
        indexService.insert(1);
        indexService.insert(2);
        indexService.insert(3);
        indexService.delete(2);
        assertFalse(indexService.search(2));
        assertThrows(NoSuchElementException.class, () -> indexService.delete(4));
    }

    @Test
    void testOccurrences() {
        indexService.insert(1);
        indexService.insert(2);
        indexService.insert(2);
        indexService.insert(2);
        indexService.insert(3);
        assertEquals(3, indexService.occurrences(2));
        assertEquals(1, indexService.occurrences(1));
        assertEquals(0, indexService.occurrences(4));
    }

    @Test
    void testRange() {
        indexService.insert(1);
        indexService.insert(2);
        indexService.insert(3);
        indexService.insert(4);
        // Caso normal
        Integer[] range = indexService.range(2, 3, true, true);
        assertArrayEquals(new Integer[]{2, 3}, range);
        // Rango vacío
        Integer[] empty = indexService.range(5, 6, true, true);
        assertEquals(0, empty.length);
        // Límite inferior exclusivo
        Integer[] exclLower = indexService.range(2, 3, false, true);
        assertArrayEquals(new Integer[]{3}, exclLower);
        // Límite superior exclusivo
        Integer[] exclUpper = indexService.range(2, 3, true, false);
        assertArrayEquals(new Integer[]{2}, exclUpper);
        // Ambos límites exclusivos
        Integer[] exclBoth = indexService.range(2, 3, false, false);
        assertEquals(0, exclBoth.length);
        // Rango invertido (min > max)
        Integer[] inverted = indexService.range(4, 1, true, true);
        assertEquals(0, inverted.length);
        // Rango con todos los elementos
        Integer[] all = indexService.range(1, 4, true, true);
        assertArrayEquals(new Integer[]{1,2,3,4}, all);
        // Rango con un solo elemento
        Integer[] single = indexService.range(2, 2, true, true);
        assertArrayEquals(new Integer[]{2}, single);
        Integer[] singleExcl = indexService.range(2, 2, false, false);
        assertEquals(0, singleExcl.length);
    }

    @Test
    void testGetMaxAndMin() {
        indexService.insert(10);
        indexService.insert(5);
        indexService.insert(20);
        assertEquals(5, indexService.getMin());
        assertEquals(20, indexService.getMax());
    }
}
