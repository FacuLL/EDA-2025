package ar.edu.itba.eda;

import java.util.function.Function;

public class ClosedHashing<K, V> implements IndexParametricService<K, V> {
	final private int initialLookupSize= 10;
	private final double threshold = 0.75;
	private int size = 0;
	private int fisicalSize = 0;

	@SuppressWarnings({"unchecked"})
	private Slot<K,V>[] Lookup= (Slot<K,V>[]) new Slot[initialLookupSize];

	private Function<? super K, Integer> prehash;

	public ClosedHashing( Function<? super K, Integer> mappingFn) {
		if (mappingFn == null)
			throw new RuntimeException("fn not provided");

		prehash= mappingFn;
	}

	// ajuste al tama�o de la tabla
	private int hash(K key) {
		if (key == null)
			throw new IllegalArgumentException("key cannot be null");

		return prehash.apply(key) % Lookup.length;
	}


	private void checkParameters(K key, V data) {
		if (key == null || data == null) {
			String msg= String.format("inserting or updating (%s,%s). ", key, data);
			if (key==null)
				msg+= "Key cannot be null. ";

			if (data==null)
				msg+= "Data cannot be null.";

			throw new IllegalArgumentException(msg);
		}
	}

	@SuppressWarnings({"unchecked"})
	private void checkRehash() {
		if ((double) fisicalSize / Lookup.length > threshold) {
			Slot<K, V>[] old = Lookup;
			Lookup = (Slot<K,V>[]) new Slot[initialLookupSize * 2];
			size = 0;
			fisicalSize = 0;
			for (Slot<K, V> slot : old) {
				if (slot != null && slot.isOccupied()) {
					insertOrUpdate(slot.key, slot.value);
				}
			}
		}
	}
	
	public void insertOrUpdate(K key, V data) {
		checkParameters(key, data);
		checkRehash();
		int index = hash(key);
		Integer firstUnoccupied = null;
		while(Lookup[index] != null && !Lookup[index].key.equals(key)) {
			if (firstUnoccupied == null && !Lookup[index].isOccupied()) firstUnoccupied = index;
			index = (index+1) % Lookup.length;
		};
		if (Lookup[index] != null && Lookup[index].key.equals(key)) {
			Lookup[index].value = data;
			Lookup[index].occupied = true;
		} else {
			Lookup[firstUnoccupied == null ? index : firstUnoccupied] = new Slot<K, V>(key, data);
			size++;
			fisicalSize++;
		}
	}
	
	// find or get
	public V find(K key) {
		if (key == null)
			return null;

		int index = hash(key);
		while (Lookup[index] != null) {
			if (Lookup[index].key.equals(key)) return Lookup[index].isOccupied() ? Lookup[index].value : null;
			index = (index+1) % Lookup.length;
		}

		return null;
	}

	public boolean remove(K key) {
		if (key == null)
			return false;
		int index = hash(key);
		while (Lookup[index] != null) {
			if (Lookup[index].key.equals(key)) {
				int next = (index+1) % Lookup.length;
				if (Lookup[next] == null) {
					Lookup[index] = null;
					fisicalSize--;
				} else
					Lookup[index].logicDelete();
				size--;
				return true;
			}
			index = (index+1) % Lookup.length;
		}
		return false;
	}

	
	public void dump()  {
		for(int rec= 0; rec < Lookup.length; rec++) {
			if (Lookup[rec] == null)
 				System.out.println(String.format("slot %d is empty", rec));
			else
				System.out.println(String.format("slot %d contains %s",rec, Lookup[rec]));
		}
	}

	public int size() {
		return size;
	}

	static private final class Slot<K, V>	{
		private final K key;
		private V value;
		private boolean occupied = true;
		
		private Slot(K theKey, V theValue){
			key= theKey;
			value= theValue;
		}

		public boolean isOccupied() {
			return occupied;
		}

		public void logicDelete() {
			occupied = false;
		}

		public String toString() {
		 return String.format("(key=%s, value=%s)", key, value );
		}
	}
	
	
//	public static void main(String[] args) {
//		ClosedHashing<Integer, String> myHash= new ClosedHashing<>(f->f);
//		myHash.insertOrUpdate(55, "Ana");
//		myHash.insertOrUpdate(44, "Juan");
//		myHash.insertOrUpdate(18, "Paula");
//		myHash.insertOrUpdate(19, "Lucas");
//		myHash.insertOrUpdate(21, "Sol");
//		myHash.dump();
//
//	}
	

	public static void main(String[] args) {
		ClosedHashing<Integer, String> myHash= new ClosedHashing<>(f->f);
		myHash.insertOrUpdate(55, "Ana");
		myHash.insertOrUpdate(29, "Victor");
		myHash.insertOrUpdate(25, "Tomas");
		myHash.insertOrUpdate(19, "Lucas");
		myHash.insertOrUpdate(21, "Sol");
		myHash.dump();
	}

}