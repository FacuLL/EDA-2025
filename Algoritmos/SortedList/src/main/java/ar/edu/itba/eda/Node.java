package ar.edu.itba.eda;

public class Node<T extends Comparable<? super T>> {
    private final T element;
    private Node<T> tail;

    public Node(T element, Node<T> tail) {
        this.element = element;
        this.tail = tail;
    }

    public Node(T element) {
        this(element, null);
    }

    public T getElement() {
        return element;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    // Para la version delegada

    public Result<T> insert(T data) {
        int cmp = element.compareTo(data);
        if (cmp == 0) return Result.Fail();
        if (cmp > 0) {
            Node<T> newNode = new Node<>(data, this);

        }
    }
}