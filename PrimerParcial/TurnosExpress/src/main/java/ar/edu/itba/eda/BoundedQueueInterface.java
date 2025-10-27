package ar.edu.itba.eda;

public interface BoundedQueueInterface<T> {

	// Nunca lanza excepcion
	boolean isEmpty();

	// Nunca lanza excepcion. 
	// Devuelve true si la bounded queue ya uso 
	// la cant de elementos solicitados en el constructor, es decir, esta llena
	boolean isFull();

	// si esta llena (el usuario debe chequearlo que no lo est�) lanza excepcion
	void enqueue(T element);

	// si esta vacia (el usuario debe chequearlo que no lo est�) lanza excepcion
	T dequeue();

}