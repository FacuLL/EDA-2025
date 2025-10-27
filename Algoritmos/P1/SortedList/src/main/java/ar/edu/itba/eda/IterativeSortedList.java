package ar.edu.itba.eda;

import java.util.NoSuchElementException;

public class IterativeSortedList<T extends Comparable<? super T>> implements SortedListService<T> {
    private Node<T> list = null;
    int size = 0;

    @Override
    public boolean insert(T data) {
        Node<T> actual = list;
        if (actual == null) {
            list = new Node<>(data);
            size++;
            return true;
        };
        while(actual.getTail() != null && actual.getTail().getElement().compareTo(data) < 0)
            actual = actual.getTail();
        if (actual.getTail().getElement().equals(data)) return false;
        actual.setTail(new Node<>(data, actual.getTail()));
        size++;
        return true;
    }

    @Override
    public boolean find(T data) {
        Node<T> actual = list;
        while(actual != null && actual.getElement().compareTo(data) < 0)
            actual = actual.getTail();
        return actual != null && actual.getElement().equals(data);
    }

    @Override
    public boolean remove(T data) {
        if (list == null) return false;
        Node<T> actual = list;
        while(actual.getTail() != null && actual.getTail().getElement().compareTo(data) < 0)
            actual = actual.getTail();
        if (!actual.getTail().getElement().equals(data)) return false;
        actual.setTail(actual.getTail().getTail());
        size--;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T getMin() {
        if (list == null) throw new NoSuchElementException();
        return list.getElement();
    }

    @Override
    public T getMax() {
        if (list == null) throw new NoSuchElementException();
        Node<T> actual = list;
        while(actual.getTail() != null) actual = actual.getTail();
        return actual.getElement();
    }

    @Override
    public void dump() {

    }
}
