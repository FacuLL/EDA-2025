package ar.edu.itba.eda;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class HashBagTest {

    @Test
    void constructorNullMapping() {
        assertThrows(RuntimeException.class, () -> new HashBag<String>(null));
    }

    @Test
    void addNullThrows() {
        HashBag<String> bag = new HashBag<>(Objects::hashCode);
        assertThrows(IllegalArgumentException.class, () -> bag.add(null));
    }

    @Test
    void removeNullDoesNotThrow() {
        HashBag<String> bag = new HashBag<>(Objects::hashCode);
        assertDoesNotThrow(() -> bag.remove(null));
    }

    @Test
    void addRemoveAndGetCountBehavior() {
        HashBag<String> bag = new HashBag<>(s -> s == null ? 0 : s.hashCode());

        assertEquals(0, bag.getCount("x"));

        bag.add("x");
        assertEquals(1, bag.getCount("x"));

        bag.add("x");
        assertEquals(2, bag.getCount("x"));

        bag.add("y");
        assertEquals(1, bag.getCount("y"));
        assertEquals(2, bag.getCount("x"));

        bag.remove("x");
        assertEquals(1, bag.getCount("x"));

        bag.remove("x");
        assertEquals(0, bag.getCount("x"));

        // removing absent element should not change counts
        bag.remove("x");
        assertEquals(0, bag.getCount("x"));
    }

    @Test
    void dumpDoesNotThrow() {
        HashBag<Integer> bag = new HashBag<>(k -> k);
        bag.add(1);
        bag.add(2);
        assertDoesNotThrow(bag::dump);
    }

}

