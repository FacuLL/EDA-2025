import ar.edu.itba.eda.ArraySort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArraySortTest {

    @Test
    public void testBubbleSort() {
        Integer[] array = {5, 3, 8, 6, 2};
        Integer[] expected = {2, 3, 5, 6, 8};
        ArraySort.bubbleSort(array);
        assertArrayEquals(expected, array);

        // Caso con un array vacío
        Integer[] emptyArray = {};
        Integer[] emptyExpected = {};
        ArraySort.bubbleSort(emptyArray);
        assertArrayEquals(emptyExpected, emptyArray);

        // Caso con un array de un elemento
        Integer[] singleElementArray = {1};
        Integer[] singleElementExpected = {1};
        ArraySort.bubbleSort(singleElementArray);
        assertArrayEquals(singleElementExpected, singleElementArray);

        // Caso con elementos repetidos
        Integer[] duplicateArray = {4, 2, 4, 3, 2};
        Integer[] duplicateExpected = {2, 2, 3, 4, 4};
        ArraySort.bubbleSort(duplicateArray);
        assertArrayEquals(duplicateExpected, duplicateArray);

        // Caso con un array ya ordenado
        Integer[] sortedArray = {1, 2, 3, 4, 5};
        Integer[] sortedExpected = {1, 2, 3, 4, 5};
        ArraySort.bubbleSort(sortedArray);
        assertArrayEquals(sortedExpected, sortedArray);
    }

    @Test
    public void testQuickSort() {
        Integer[] array = {5, 3, 8, 6, 2};
        Integer[] expected = {2, 3, 5, 6, 8};
        ArraySort.quickSort(array);
        assertArrayEquals(expected, array);

        // Caso con un array vacío
        Integer[] emptyArray = {};
        Integer[] emptyExpected = {};
        ArraySort.quickSort(emptyArray);
        assertArrayEquals(emptyExpected, emptyArray);

        // Caso con un array de un elemento
        Integer[] singleElementArray = {1};
        Integer[] singleElementExpected = {1};
        ArraySort.quickSort(singleElementArray);
        assertArrayEquals(singleElementExpected, singleElementArray);

        // Caso con elementos repetidos
        Integer[] duplicateArray = {4, 2, 4, 3, 2};
        Integer[] duplicateExpected = {2, 2, 3, 4, 4};
        ArraySort.quickSort(duplicateArray);
        assertArrayEquals(duplicateExpected, duplicateArray);

        // Caso con un array ya ordenado
        Integer[] sortedArray = {1, 2, 3, 4, 5};
        Integer[] sortedExpected = {1, 2, 3, 4, 5};
        ArraySort.quickSort(sortedArray);
        assertArrayEquals(sortedExpected, sortedArray);
    }

    @Test
    public void testMergeSort() {
        Integer[] array = {5, 3, 8, 6, 2};
        Integer[] expected = {2, 3, 5, 6, 8};
        ArraySort.mergeSort(array);
        assertArrayEquals(expected, array);

        // Caso con un array vacío
        Integer[] emptyArray = {};
        Integer[] emptyExpected = {};
        ArraySort.mergeSort(emptyArray);
        assertArrayEquals(emptyExpected, emptyArray);

        // Caso con un array de un elemento
        Integer[] singleElementArray = {1};
        Integer[] singleElementExpected = {1};
        ArraySort.mergeSort(singleElementArray);
        assertArrayEquals(singleElementExpected, singleElementArray);

        // Caso con elementos repetidos
        Integer[] duplicateArray = {4, 2, 4, 3, 2};
        Integer[] duplicateExpected = {2, 2, 3, 4, 4};
        ArraySort.mergeSort(duplicateArray);
        assertArrayEquals(duplicateExpected, duplicateArray);

        // Caso con un array ya ordenado
        Integer[] sortedArray = {1, 2, 3, 4, 5};
        Integer[] sortedExpected = {1, 2, 3, 4, 5};
        ArraySort.mergeSort(sortedArray);
        assertArrayEquals(sortedExpected, sortedArray);
    }
}