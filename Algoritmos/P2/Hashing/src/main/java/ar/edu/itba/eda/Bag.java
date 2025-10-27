package ar.edu.itba.eda;

public interface Bag<T>{

    void add(T value);

    void remove(T value);

    int getCount(T value);

    void dump();

}
