package ar.edu.itba.eda.core;

public class Node<T extends Comparable<? super T>> implements NodeTreeInterface<T> {
    private T data;
    private Node<T> left;
    private Node<T> right;

    public Node(T data, Node<T> left, Node<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public Node<T> getLeft() {
        return left;
    }

    @Override
    public Node<T> getRight() {
        return right;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    private Node<T> getGreater() {
        if (left != null) return left.getGreater();
        return this;
    }

    public Node<T> delete(T data) {
        int cmp = this.data.compareTo(data);
        if (cmp > 0) {
            if (left != null) left = left.delete(data);
            return this;
        }
        if (cmp < 0) {
            if (right != null) right = right.delete(data);
            return this;
        }
        if (left == null) return right;
        if (right == null) return left;
        T replacement = left.getGreater().getData();
        left = left.delete(replacement);
        this.data = replacement;
        return this;
    }
}
