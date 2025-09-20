package ar.edu.itba.eda;

public interface IndexService {

    void initialize(String [] elements);
    // Busca una key en el índice. Devuelve true si está en el índice y false en caso contrario
    boolean search(String key);
    // Inserta el key en posición correcta.
    // Crece automáticamente de a chunks!!
    void insert(String key);
    // Borra el key si lo hay, sino lo ignora. Si hubiera más de uno, borra cualquiera de ellos.
    // Decrece automáticamente de a chunks
    void delete(String key);

    String[] range(String leftKey, String rightKey);
    // Devuelve la cantidad de apariciones de la clave especificada
    long occurrences(String key);
    // Devuelve la mayor clave
    String getMax();
    // Devuelve la menor clave
    String getMin();
}
