package ar.edu.itba.eda;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class IndexServiceImpl<T extends Comparable<? super T>> implements IndexService<T> {
    private final Class<T> tClass;

    private final static int CHUNK_SIZE = 10;
    private T[] elements;
    private int length = 0;

    public IndexServiceImpl(Class<T> tClass) {
        this.tClass = tClass;
        this.elements = (T[]) Array.newInstance(tClass, CHUNK_SIZE);
    }

    @Override
    public void initialize(T[] elements) {
        if (elements == null) throw new IllegalArgumentException();
        for (T element : elements) {
            this.insert(element);
        }
    }

    private int binarySearch(T key, int izq, int der) {
        if (der < izq) return izq;
        int mid = (der + izq) / 2;
        T actual = elements[mid];
        if (key.equals(actual)) return mid;
        if (key.compareTo(actual) > 0) return binarySearch(key, mid+1, der);
        return binarySearch(key, izq, mid-1);
    }

    private int getClosestPosition(T key) {
        return binarySearch(key, 0, length-1);
    }

    @Override
    public boolean search(T key) {
        int found = getClosestPosition(key);
        return key.equals(elements[found]);
    }

    private void moveUp(int from) {
        for (int i = length; i > from; i--) {
            elements[i] = elements[i-1];
        }
    }

    @Override
    public void insert(T key) {
        int position = getClosestPosition(key);
        if (length % CHUNK_SIZE == 0) elements = Arrays.copyOf(elements, length + CHUNK_SIZE);
        moveUp(position);
        elements[position] = key;
        length++;
    }

    private void moveDown(int from) {
        for (int i = from; i < length; i++) {
            elements[i] = elements[i+1];
        }
    }

    @Override
    public void delete(T key) {
        int position = getClosestPosition(key);
        T found = elements[position];
        if (key.equals(found)) {
            moveDown(position);
            length--;
            if (length % CHUNK_SIZE == 0) elements = Arrays.copyOf(elements, length + CHUNK_SIZE);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int occurrences(T key) {
        int occurences = 0;
        int position = getClosestPosition(key);
        int toRight = position;
        T found = elements[position];
        if (key.equals(found)) {
            while (position >= 0 && elements[position--].equals(found)) occurences++;
            while (toRight+1 < length && elements[++toRight].equals(found)) occurences++;
        }
        return occurences;
    }

    private boolean validIndex(int index) {
        return index >= 0 && index < length;
    }

    private int getBorderIndex(T key, int outsideDirection, boolean included) {
        int index = getClosestPosition(key);
        if (included) {
            while (validIndex(index+outsideDirection) && key.equals(elements[index+outsideDirection])) index+=outsideDirection;
        } else {
            while (validIndex(index-outsideDirection) && key.equals(elements[index])) index-=outsideDirection;
        }
        return index;
    }

    @Override
    public T[] range(T leftKey, T rightKey, boolean leftIncluded, boolean rightIncluded) {
        int leftIndex = getBorderIndex(leftKey, -1, leftIncluded);
        int rightIndex = getBorderIndex(rightKey, 1, rightIncluded);
        if (leftIndex > rightIndex || !validIndex(leftIndex) || !validIndex(rightIndex)) return (T[]) Array.newInstance(tClass, 0);
        T[] toReturn = (T[]) Array.newInstance(tClass, rightIndex-leftIndex+1);
        for (int i = leftIndex; i <= rightIndex; i++) {
            toReturn[i-leftIndex] = elements[i];
        }
        return toReturn;
    }

    @Override
    public void sortedPrint() {
        for (T element : elements) {
            System.out.println(element);
        }
    }

    @Override
    public T getMax() {
        return elements[length-1];
    }

    @Override
    public T getMin() {
        return elements[0];
    }
}
