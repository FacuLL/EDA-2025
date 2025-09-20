package ar.edu.itba.eda;

import java.util.Arrays;

public class CompactIndex implements IndexService {
    private IndexCount[] elements = new IndexCount[0];
    private final int chunkSize = 4;
    private int size = 0;

    // Estructura para guardar la cantidad de ocurrencias de cada índice
    class IndexCount {
        String index;
        long count;

        public IndexCount(String index) {
            this.index = index;
            this.count = 1;
        }
    }

    @Override
    public void initialize(String[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException("elements no puede ser null");
        }
        this.elements = new IndexCount[0];
        this.size = 0;

        for(String element : elements) {
            insert(element);
        }
    }

    private int binarySearch(String key, int izq, int der) {
        if (izq >= der) return izq;
        int mid = (izq + der) / 2;
        int cmp = elements[mid].index.compareTo(key);
        if (cmp == 0) return mid;
        if (cmp > 0) return binarySearch(key, izq, mid-1);
        return binarySearch(key, mid+1, der);
    }

    private int findIndex(String key) {
        return binarySearch(key, 0, size);
    }

    @Override
    public void insert(String key) {
        int index = findIndex(key);
        if (elements[index].index.equals(key)) {
            elements[index].count++;
        } else {
            IndexCount[] array = elements;
            if (size % chunkSize == 0) {
                array = new IndexCount[size+chunkSize];
                for (int i = 0; i < index; i++) {
                    array[i] = elements[i];
                }
            }
            for (int i = size - 1; i >= index; i--) {
                array[i+1] = elements[i];
            }
            array[index] = new IndexCount(key);
            elements = array;
        }
        size++;
    }

    @Override
    public long occurrences(String key) {
        int index = findIndex(key);
        if (elements[index].index.equals(key)) return elements[index].count;
        return 0;
    }

    @Override
    public String getMax() {
        if (size == 0) {
            return null;
        }
        return elements[size-1].index;
    }

    @Override
    public String getMin() {
        if (size == 0) {
            return null;
        }
        return elements[size-1].index;
    }

    @Override
    public boolean search(String key) {
        return false;
    }

    @Override
    public void delete(String key) {

    }

    @Override
    public String[] range(String leftKey, String rightKey) {
        return new String[0];
    }
}