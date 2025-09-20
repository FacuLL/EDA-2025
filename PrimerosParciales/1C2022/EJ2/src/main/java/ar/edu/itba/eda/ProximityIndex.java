package ar.edu.itba.eda;

public class ProximityIndex {
    private String[] elements;
    private int size = 0;

    public void initialize(String[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException("elements no puede ser null");
        }
        
        for(int rec= 0; rec < elements.length-1; rec++) {
        	if (elements[rec].compareTo(elements[rec+1]) >= 0)
                throw new IllegalArgumentException("hay repetidos o no est� ordenado");
        }
        
        this.elements = elements;
        this.size = elements.length;
    }

    private Integer binarySearch(String key, int izq, int der) {
        if (izq > der) return null;
        int mid = (der + izq) / 2;
        String actual = elements[mid];
        if (actual.equals(key)) return mid;
        if (actual.compareTo(key) < 0) return binarySearch(key, mid+1, der);
        return binarySearch(key, izq, mid-1);
    }

    private Integer binarySearch(String key) {
        return binarySearch(key, 0, size-1);
    }

    public String search(String element, int distance) {
        Integer result = binarySearch(element);
        if (result == null) return null;
        int index = (result + distance) % size;
        if (index < 0) index += size;
        return elements[index];
    }

   
}
