package ar.edu.itba.eda;

import java.util.NoSuchElementException;

public class BoundedQueue<T> {
    private final int maxSize;
    private final T[] queue;
    private int size = 0;
    private int head = 0;
    private int tail = 0;

    public BoundedQueue(int maxSize) {
        this.maxSize = maxSize;
        this.queue = (T[]) new Object[maxSize];
    }

    public void enqueue(T element) {
        if (size == maxSize) throw new IndexOutOfBoundsException();
        queue[tail] = element;
        tail = (tail + 1) % maxSize;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        T element = queue[head];
        head = (head + 1) % maxSize;
        size--;
        return element;
    }

    public T peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return queue[head];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == maxSize;
    }

    public void dump() {
        while (!isEmpty())
            System.out.println(dequeue());
    }

    @Override
    public String toString() {
        if (size == 0) return "";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(queue[(head+i)%queue.length]);
            builder.append(" ");
        }
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }

    public static void main(String[] args) {
        BoundedQueue<Integer> myQueue = new BoundedQueue<>(5);
        myQueue.enqueue(10);
        myQueue.enqueue(20);
        myQueue.enqueue(30);
        myQueue.enqueue(40);
        myQueue.enqueue(50);
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue.dequeue());
        myQueue.enqueue(50);
        System.out.println("\nquedaron 5 elementos");
        myQueue.dump();
    }

}
