
import ar.edu.itba.eda.BoundedQueue;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BoundedQueueTest {

    @Test
    void testEnqueueDequeue() {
        BoundedQueue<Integer> queue = new BoundedQueue<>(3);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void testPeek() {
        BoundedQueue<String> queue = new BoundedQueue<>(2);

        queue.enqueue("A");
        queue.enqueue("B");

        assertEquals("A", queue.peek()); // primer elemento
        assertEquals("A", queue.peek()); // peek no lo remueve
        assertEquals("A", queue.dequeue()); // ahora sí lo saca
    }

    @Test
    void testEnqueueFullQueueThrows() {
        BoundedQueue<Integer> queue = new BoundedQueue<>(2);

        queue.enqueue(10);
        queue.enqueue(20);

        assertThrows(IndexOutOfBoundsException.class, () -> queue.enqueue(30));
    }

    @Test
    void testDequeueEmptyThrows() {
        BoundedQueue<Integer> queue = new BoundedQueue<>(2);

        assertThrows(NoSuchElementException.class, queue::dequeue);
    }

    @Test
    void testPeekEmptyThrows() {
        BoundedQueue<Integer> queue = new BoundedQueue<>(2);

        assertThrows(NoSuchElementException.class, queue::peek);
    }

    @Test
    void testCircularBehavior() {
        BoundedQueue<Integer> queue = new BoundedQueue<>(3);

        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(1, queue.dequeue());
        queue.enqueue(3);
        queue.enqueue(4);

        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());
        assertTrue(queue.isEmpty());
    }
}
