package ar.edu.itba.eda;

import java.util.NoSuchElementException;

public class DelegatedSortedList<T extends Comparable<? super T>> implements SortedListService<T> {
    private Node<T> list = null;
    int size = 0;

    @Override
    public boolean insert(T data) {
        if (list == null) {
            list = new Node<>(data);
            size++;
            return true;
        }
        Result<T> result = list.insert(data);
        list = result.getNewList();
        if (result.isSuccess()) size++;
        return result.isSuccess();
    }

    @Override
    public boolean find(T data) {
        Result<T> result = list.find(data);
        return result.isSuccess();
    }

    @Override
    public boolean remove(T data) {
        Result<T> result = list.delete(data);
        this.list = result.getNewList();
        if (result.isSuccess()) size--;
        return result.isSuccess();
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
        if (size == 0) throw new NoSuchElementException();
        return this.list.getElement();
    }

    @Override
    public T getMax() {
        if (size == 0) throw new NoSuchElementException();
        return this.list.getLast().getElement();
    }

    @Override
    public void dump() {

    }
}
