package ar.edu.itba.eda;

import java.util.Arrays;

public class ArraySort {
    private static <T> void swap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static <T extends Comparable<? super T>> void bubbleSort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j+1);
                }
            }
        }
    }

    private static <T extends Comparable<? super T>> void quickSortRec(T[] array, int izq, int der) {
        if (der - izq <= 1) return;
        int pivoteIndex = der;
        int minIndex = izq-1;
        for (int i = izq; i < pivoteIndex; i++) {
            if (array[i].compareTo(array[pivoteIndex]) < 0) {
                swap(array, ++minIndex, i);
            }
        }
        swap(array, minIndex+1, pivoteIndex);
        pivoteIndex = minIndex+1;
        quickSortRec(array, izq, pivoteIndex-1);
        quickSortRec(array, pivoteIndex+1, der);
    }

    public static <T extends Comparable<? super T>> void quickSort(T[] array) {
        quickSortRec(array, 0, array.length-1);
    }

    private static <T extends Comparable<? super T>> T[] mergeSortRec(T[] array) {
        if (array.length <= 1) return array;
        int mid = array.length / 2;
        T[] left = mergeSortRec(Arrays.copyOfRange(array, 0, mid));
        T[] right = mergeSortRec(Arrays.copyOfRange(array, mid, array.length));
        int i = 0, j = 0;
        while (i < left.length || j < right.length) {
            if (i == left.length) array[i+j] = right[j++];
            else if (j == right.length) array[i+j] = left[i++];
            else if (left[i].compareTo(right[j]) < 0) array[i+j] = left[i++];
            else array[i+j] = right[j++];
        }
        return array;
    }

    public static <T extends Comparable<? super T>> void mergeSort(T[] array) {
        mergeSortRec(array);
    }
}
