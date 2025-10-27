package ar.edu.itba.eda;

import java.util.NoSuchElementException;

public class BoundedQueue<T> implements BoundedQueueInterface<T> {
	private final T[] queue;
	private int size = 0;
	private int head = 0;
	private int tail = 0;
	
	@SuppressWarnings("unchecked")
	public BoundedQueue(int limit) {
		queue = (T[]) new Object[limit];
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	
	@Override
	public boolean isFull() {
		return size == queue.length;
	}
	
	@Override
	public void enqueue(T element) {
		if (isFull()) throw new IndexOutOfBoundsException();
		queue[tail] = element;
		tail = (tail + 1) % queue.length;
		size++;
	  }
	
	@Override
	public T dequeue()  {
		if (isEmpty()) throw new NoSuchElementException();
		T element = queue[head];
		head = (head + 1) % queue.length;
		size--;
		return element;
	}
	
	
}
