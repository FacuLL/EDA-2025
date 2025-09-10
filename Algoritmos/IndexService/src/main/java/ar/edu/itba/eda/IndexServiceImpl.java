package ar.edu.itba.eda;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class IndexServiceImpl implements IndexService {
    private final static int CHUNK_SIZE = 10;
    private Element[] elements;
    private int length = 0;

    public IndexServiceImpl() {
        this.elements = new Element[CHUNK_SIZE];
    }

    @Override
    public void initialize(int[] elements) {
        if (elements == null) throw new IllegalArgumentException();
        for (int element : elements) {
            this.insert(element);
        }
    }

    private int binarySearch(int key, int izq, int der) {
        int mid = (der + izq) / 2;
        int actual = elements[mid].getKey();
        if (actual == key || (mid == izq && mid == der)) return mid;
        if (actual < key) return binarySearch(key, mid+1, der);
        return binarySearch(key, izq, mid-1);
    }

    private int getClosestPosition(int key) {
        return binarySearch(key, 0, elements.length-1);
    }

    @Override
    public boolean search(int key) {
        int found = getClosestPosition(key);
        return elements[found].getKey() == key;
    }

    private void moveUp(int from) {
        for (int i = length; i > from; i--) {
            elements[i] = elements[i-1];
        }
    }

    @Override
    public void insert(int key) {
        int position = getClosestPosition(key);
        Element found = elements[position];
        if (found.getKey() == key) {
            found.addOccurrences();
        } else {
            if (length % CHUNK_SIZE == 0) elements = Arrays.copyOf(elements, length + CHUNK_SIZE);
            moveUp(position);
            elements[position] = new Element(key);
            length++;
        }
    }

    private void moveDown(int from) {
        for (int i = from; i < length; i++) {
            elements[i] = elements[i+1];
        }
    }

    @Override
    public void delete(int key) {
        int position = getClosestPosition(key);
        Element found = elements[position];
        if (found.getKey() == key) {
            found.deleteOccurrence();
            if (found.getOccurrences() == 0) {
                moveDown(position);
                length--;
                if (length % CHUNK_SIZE == 0) elements = Arrays.copyOf(elements, length + CHUNK_SIZE);
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int occurrences(int key) {
        int position = getClosestPosition(key);
        Element found = elements[position];
        if (found.getKey() == key) return found.getOccurrences();
        return 0;
    }

    private static class Element {
        private final int key;
        private int occurrences = 1;

        public Element(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public int getOccurrences() {
            return occurrences;
        }

        public void addOccurrences() {
            this.occurrences++;
        }

        public void deleteOccurrence() {
            this.occurrences--;
        }
    }
}
