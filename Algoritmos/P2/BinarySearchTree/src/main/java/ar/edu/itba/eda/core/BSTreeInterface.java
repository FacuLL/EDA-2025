package ar.edu.itba.eda.core;


public interface BSTreeInterface<T extends Comparable<? super T>> extends Iterable<T> {

	enum Traversal { BYLEVELS, INORDER };

	void setTraversal(Traversal traversal);

	void insert(T myData);

	void preOrder();

	void postOrder();

	void inOrder();

	NodeTreeInterface<T> getRoot();
	
	int getHeight();

}