package ar.edu.itba.eda;

import java.util.NoSuchElementException;

public class RecursiveSortedList<T extends Comparable<? super T>> implements SortedListService<T> {
    private Node<T> list = null;
    int size = 0;

    private Result<T> insertRec(T data, Node<T> node) {
        if (node == null) return Result.Success(new Node<>(data, null));
        int cmp = node.getElement().compareTo(data);
        if (cmp == 0) return Result.Fail();
        if (cmp > 0) return Result.Success(new Node<>(data, node));
        Result<T> result = insertRec(data, node.getTail());
        if (result.isSuccess()) node.setTail(result.getNewList());
        return result.setNewList(node);
    }

    @Override
    public boolean insert(T data) {
        Result<T> result = insertRec(data, list);
        if (!result.isSuccess()) return false;
        list = result.getNewList();
        size++;
        return true;
    }

    private Node<T> findRec(T value, Node<T> node) {
        if (node == null) return null;
        int cmp = node.getElement().compareTo(value);
        if (cmp == 0) return node;
        if (cmp > 0) return null;
        return findRec(value, node.getTail());
    }

    @Override
    public boolean find(T data) {
        Node<T> found = findRec(data, list);
        return found != null;
    }

    private Result<T> removeRec(T data, Node<T> node) {
        if (node == null) return Result.Fail();
        int cmp = node.getElement().compareTo(data);
        if (cmp == 0) return Result.Success(node.getTail());
        if (cmp > 0) return Result.Fail();
        Result<T> result = removeRec(data, node.getTail());
        if (result.isSuccess()) node.setTail(result.getNewList());
        return result.setNewList(node);
    }

    @Override
    public boolean remove(T data) {
        Result<T> result = removeRec(data, list);
        if (result.isSuccess()) size--;
        return result.isSuccess();
    }

    @Override
    public boolean isEmpty() {
        return list == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T getMin() {
        if (isEmpty()) throw new NoSuchElementException();
        return list.getElement();
    }

    @Override
    public T getMax() {
        if (isEmpty()) throw new NoSuchElementException();
        Node<T> last = list;
        while (last.getTail() != null) last = last.getTail();
        return last.getElement();
    }

    @Override
    public void dump() {

    }

}