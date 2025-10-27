package ar.edu.itba.eda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClosedHashingTest {

    @Test
    void constructorNullMapping() {
        assertThrows(RuntimeException.class, () -> new ClosedHashing<Integer, String>(null));
    }

    @Test
    void insertOrUpdateNullKeyOrValue() {
        ClosedHashing<Integer, String> h = new ClosedHashing<>(k -> k);
        assertThrows(IllegalArgumentException.class, () -> h.insertOrUpdate(null, "x"));
        assertThrows(IllegalArgumentException.class, () -> h.insertOrUpdate(1, null));
    }

    @Test
    void insertFindUpdateAndSize() {
        ClosedHashing<Integer, String> h = new ClosedHashing<>(k -> k);
        assertEquals(0, h.size());

        h.insertOrUpdate(1, "one");
        assertEquals(1, h.size());
        assertEquals("one", h.find(1));

        h.insertOrUpdate(1, "uno");
        assertEquals(1, h.size(), "size should not increase when updating the same key");
        assertEquals("uno", h.find(1));

        h.insertOrUpdate(2, "two");
        assertEquals(2, h.size());
        assertEquals("two", h.find(2));
    }

    @Test
    void findAndRemoveWithNullKey() {
        ClosedHashing<Integer, String> h = new ClosedHashing<>(k -> k);
        assertNull(h.find(null));
        assertFalse(h.remove(null));
    }

    @Test
    void collisionAndProbingBehavior() {
        // mapping that forces all keys to collide (same prehash)
        ClosedHashing<Integer, String> h = new ClosedHashing<>(k -> 0);

        // insert several colliding keys
        for (int i = 1; i <= 5; i++) {
            h.insertOrUpdate(i, "v" + i);
        }
        assertEquals(5, h.size());

        // all keys must be findable
        for (int i = 1; i <= 5; i++) {
            assertEquals("v" + i, h.find(i));
        }

        // remove a middle key
        assertTrue(h.remove(3));
        assertNull(h.find(3));
        assertEquals(4, h.size());

        // other keys still present
        assertEquals("v1", h.find(1));
        assertEquals("v2", h.find(2));
        assertEquals("v4", h.find(4));
        assertEquals("v5", h.find(5));

        // inserting a new key should reuse a logically deleted slot or occupy free space
        h.insertOrUpdate(8, "v8");
        assertEquals("v8", h.find(8));
        assertEquals(5, h.size());
    }

    @Test
    void rehashTriggerAndIntegrity() {
        // identity prehash; initial table length is 10 and threshold is 0.75 => rehash happens when fisicalSize/len > 0.75
        ClosedHashing<Integer, String> h = new ClosedHashing<>(k -> k);

        // insert 8 items to cross the 0.75 threshold (8/10 = 0.8)
        for (int i = 1; i <= 8; i++) {
            h.insertOrUpdate(i, "val" + i);
        }

        assertEquals(8, h.size());

        // after rehash (or reinsertion) all values must still be available
        for (int i = 1; i <= 8; i++) {
            assertEquals("val" + i, h.find(i), "value for key " + i + " should be present after rehash");
        }
    }

}

