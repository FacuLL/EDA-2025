package ar.edu.itba.eda;

import java.util.LinkedList;
import java.util.function.Function;

public class OpenHashing<K, V> {
    final private int initialLookupSize= 10;
    private final double threshold = 0.75;
    private int size = 0;

    @SuppressWarnings({"unchecked"})
    private LinkedList<Slot<K,V>>[] Lookup = new LinkedList[initialLookupSize];

    private Function<? super K, Integer> prehash;

    public OpenHashing(Function<? super K, Integer> mappingFn) {
        if (mappingFn == null)
            throw new RuntimeException("fn not provided");

        prehash= mappingFn;
    }

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
        if ((double) size / Lookup.length > threshold) {
            LinkedList<Slot<K, V>>[] old = Lookup;
            Lookup = (LinkedList<Slot<K, V>>[]) new LinkedList[initialLookupSize * 2];
            size = 0;
            for (LinkedList<Slot<K, V>> list : old) {
                if (list != null) {
                    for (Slot<K, V> slot : list) {
                        insertOrUpdate(slot.key, slot.value);
                    }
                }
            }
        }
    }

    public void insertOrUpdate(K key, V data) {
        checkParameters(key, data);
        checkRehash();

        LinkedList<Slot<K, V>> found = Lookup[hash(key)];
        if (found == null) found = Lookup[hash(key)] = new LinkedList<>();
        for (Slot<K, V> slot : found) {
            if (slot.key.equals(key)) {
                slot.value = data;
                return;
            }
        }
        found.add(new Slot<>(key, data));
        size++;
    }

    // find or get
    public V find(K key) {
        if (key == null)
            return null;

        LinkedList<Slot<K, V>> found = Lookup[hash(key)];
        if (found == null) return null;
        for (Slot<K, V> slot : found) {
            if (slot.key.equals(key)) return slot.value;
        }
        return null;
    }

    public boolean remove(K key) {
        if (key == null)
            return false;
        int index = hash(key);

        LinkedList<Slot<K, V>> found = Lookup[hash(key)];
        if (found == null) return false;
        boolean success = found.remove(new Slot<K, V>(key, null));
        if (success) size--;
        return success;
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

        private Slot(K theKey, V theValue){
            key= theKey;
            value= theValue;
        }

        public String toString() {
            return String.format("(key=%s, value=%s)", key, value );
        }

        @Override
        public boolean equals(Object obj) {
            if (!obj.getClass().equals(getClass())) return false;
            Slot<K, V> slot = (Slot<K, V>) obj;
            if (!slot.key.getClass().equals(key.getClass())) return false;
            return key.equals(((Slot<?, ?>) obj).key);
        }
    }


//	public static void main(String[] args) {
//		OpenHasing<Integer, String> myHash= new OpenHasing<>(f->f);
//		myHash.insertOrUpdate(55, "Ana");
//		myHash.insertOrUpdate(44, "Juan");
//		myHash.insertOrUpdate(18, "Paula");
//		myHash.insertOrUpdate(19, "Lucas");
//		myHash.insertOrUpdate(21, "Sol");
//		myHash.dump();
//
//	}


    public static void main(String[] args) {
        OpenHashing<Integer, String> myHash= new OpenHashing<>(f->f);
        myHash.insertOrUpdate(55, "Ana");
        myHash.insertOrUpdate(29, "Victor");
        myHash.insertOrUpdate(25, "Tomas");
        myHash.insertOrUpdate(19, "Lucas");
        myHash.insertOrUpdate(21, "Sol");
        myHash.dump();
    }
}
