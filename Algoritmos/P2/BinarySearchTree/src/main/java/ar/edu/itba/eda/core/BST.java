package ar.edu.itba.eda.core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<T extends Comparable<? super T>> implements BSTreeInterface<T> {

    private Node<T> root;
    private Traversal traversal;

    public Node<T> insertRec(Node<T> node, T data) {
        if (node == null) return new Node<>(data, null, null);
        int cmp = node.getData().compareTo(data);
        if (cmp == 0) return node;
        if (cmp > 0) node.setLeft(insertRec(node.getLeft(), data));
        else node.setRight(insertRec(node.getRight(), data));
        return node;
    }

    @Override
    public void insert(T myData) {
        this.root = insertRec(root, myData);
    }

    @Override
    public void preOrder() {

    }

    @Override
    public void postOrder() {

    }

    @Override
    public void inOrder() {
        Stack<Node<T>> stack = new Stack<>();
        Node<T> actual = root;
        while(!stack.isEmpty() || actual != null) {
            if (actual != null) {
                stack.push(actual);
                actual = actual.getLeft();
            }
            else {

            }
        }
    }

    @Override
    public NodeTreeInterface<T> getRoot() {
        return null;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void setTraversal(Traversal traversal) {
        this.traversal = traversal;
    }

    @Override
    public Iterator<T> iterator() {
        Queue<Node<T>> queue = new LinkedList<>();
        Node<T> actual = root;
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        }
    }
}
