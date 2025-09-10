package ar.edu.itba.eda;

public class DelegatedSortedList<T extends Comparable<? super T>> implements SortedListService<T> {
    @Override
    public boolean insert(T data) {
        return false;
    }

    @Override
    public boolean find(T data) {
        return false;
    }

    @Override
    public boolean remove(T data) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T getMin() {
        return null;
    }

    @Override
    public T getMax() {
        return null;
    }

    @Override
    public void dump() {

    }
}
