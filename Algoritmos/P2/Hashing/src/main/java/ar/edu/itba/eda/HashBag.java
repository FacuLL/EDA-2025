package ar.edu.itba.eda;

import java.util.function.Function;

public class HashBag<T> implements Bag<T> {
    private ClosedHashing<T, Integer> hashMap;

    public HashBag(Function<T, Integer> function) {
        this.hashMap = new ClosedHashing<>(function);
    }

    @Override
    public void add(T value) {
        Integer found = hashMap.find(value);
        hashMap.insertOrUpdate(value, found == null ? 1 : found+1);
    }

    @Override
    public void remove(T value) {
        Integer found = hashMap.find(value);
        if (found == null) return;
        if (found <= 1) hashMap.remove(value);
        else hashMap.insertOrUpdate(value, found-1);
    }

    @Override
    public int getCount(T value) {
        Integer found = hashMap.find(value);
        return found == null ? 0 : found;
    }

    @Override
    public void dump() {
        hashMap.dump();
    }
}
