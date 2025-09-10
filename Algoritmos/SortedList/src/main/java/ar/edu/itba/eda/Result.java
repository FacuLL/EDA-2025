package ar.edu.itba.eda;

public class Result<T extends Comparable<? super T>> {
    private final boolean success;
    private final Node<T> newList;

    public Result(boolean success, Node<T> newList) {
        this.success = success;
        this.newList = newList;
    }

    public Result<T> setNewList(Node<T> newList) {
        return new Result<>(this.isSuccess(), newList);
    }

    public static <K extends Comparable<? super K>> Result<K> Success(Node<K> newList) {
        return new Result<>(true, newList);
    }

    public static <K extends Comparable<? super K>> Result<K> Fail() {
        return new Result<>(false, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public Node<T> getNewList() {
        return newList;
    }
}
