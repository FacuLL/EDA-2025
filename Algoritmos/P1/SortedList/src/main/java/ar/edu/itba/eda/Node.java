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
            newNode.tail = this;
            return Result.Success(newNode);
        }
        if (this.tail == null) {
            Node<T> newNode = new Node<>(data, null);
            this.tail = newNode;
            return Result.Success(this);
        }
        Result<T> result = this.tail.insert(data);
        this.tail = result.getNewList();
        return result.setNewList(this);
    }

    public Result<T> find(T data) {
        int cmp = element.compareTo(data);
        if (cmp == 0) return Result.Success(this);
        if (cmp > 0 || this.tail == null) return Result.Fail();
        return this.tail.find(data);
    }

    public Result<T> delete(T data) {
        int cmp = element.compareTo(data);
        if (cmp == 0) return Result.Success(this.tail);
        if (cmp > 0 || this.tail == null) return Result.Fail();
        Result<T> result = this.tail.delete(data);
        this.tail = result.getNewList();
        return result.setNewList(this);
    }

    public Node<T> getLast() {
        if (this.tail == null) return this;
        return this.tail.getLast();
    }
}