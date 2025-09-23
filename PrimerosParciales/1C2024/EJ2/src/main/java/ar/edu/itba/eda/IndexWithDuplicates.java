package ar.edu.itba.eda;

import java.util.Arrays;

/**
 * @author dpenaloza
 *
 */
public class IndexWithDuplicates  {

	final static private int chunksize= 5;

	private int[] indexedData;
	private int cantElems;
	
	
	public IndexWithDuplicates() {
		indexedData= new int[chunksize];
		cantElems= 0;
	}

	public void initialize(int[] unsortedElements) {

		if (unsortedElements == null)
			throw new RuntimeException("Problem: null data collection");

		indexedData= unsortedElements;
		Arrays.sort(indexedData);
		cantElems= indexedData.length;
	}

	public int[] getIndexedData() {
		return indexedData;
	}

	public void print() {
		System.out.print(this);
	}

	public String toString() {
		StringBuilder toReturn = new StringBuilder();
		toReturn.append("[");
		for (int i : indexedData)
			toReturn.append(i).append(", ");
		toReturn.delete(toReturn.length()-2, toReturn.length());
		toReturn.append("]");
		return toReturn.toString();
	}

	public void merge(IndexWithDuplicates other) {
		int[] newIndex = new int[cantElems + other.cantElems];
		int i = 0, j = 0;
		while (i < cantElems || j < other.cantElems) {
			if (i >= cantElems) newIndex[i+j] = other.indexedData[j++];
			else if (j >= other.cantElems) newIndex[i+j] = indexedData[i++];
			else if (indexedData[i] <= other.indexedData[j]) newIndex[i+j] = indexedData[i++];
			else newIndex[i+j] = other.indexedData[j++];
		}
		indexedData = newIndex;
	}


}

