package ar.edu.itba.eda.core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<T extends Comparable<? super T>> implements BSTreeInterface<T> {

    private Node<T> root;
    private Traversal traversal = Traversal.BYLEVELS;

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

    private void preOrder(Node<T> node) {
        System.out.print(node.getData() + " ");
        if (node.getLeft() != null) preOrder(node.getLeft());
        if (node.getRight() != null) preOrder(node.getRight());
    }

    @Override
    public void preOrder() {
        preOrder(root);
    }

    private void postOrder(Node<T> node) {
        if (node.getLeft() != null) postOrder(node.getLeft());
        System.out.print(node.getData() + " ");
        if (node.getRight() != null) postOrder(node.getRight());
    }

    @Override
    public void postOrder() {
        postOrder(root);
    }

    private void inOrder(Node<T> node) {
        if (node.getLeft() != null) inOrder(node.getLeft());
        System.out.print(node.getData() + " ");
        if (node.getRight() != null) inOrder(node.getRight());
    }

    @Override
    public void inOrder() {
        inOrder(root);
    }

    public void delete(T data) {
        root.delete(data);
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
    public Iterator<T> iterator(){
        return switch (traversal){
            case BYLEVELS -> new LevelIterator();
            case INORDER -> new InOrderIterator();
        };
    }

    private class LevelIterator implements Iterator<T>{

        private final Queue<NodeTreeInterface<T>> queue;

        private LevelIterator() {
            queue = new LinkedList<>();

            if (root != null)
                queue.add(root);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            NodeTreeInterface<T> currentNode = queue.remove();

            if (currentNode.getLeft() != null)
                queue.add(currentNode.getLeft());

            if (currentNode.getRight() != null)
                queue.add(currentNode.getRight());

            return currentNode.getData();
        }

    }

    private class InOrderIterator implements Iterator<T>{

        private final Stack<NodeTreeInterface<T>> stack;
        NodeTreeInterface<T> current;

        private InOrderIterator() {
            stack = new Stack<>();
            current = root;
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty() || current != null;
        }

        @Override
        public T next() {
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }
            NodeTreeInterface<T> nodeToProcess = stack.pop();
            current = nodeToProcess.getRight();
            return nodeToProcess.getData();
        }
    }

    public static void main(String[] args) {
        BST<Integer> myTree = new BST<>();
        myTree.insert(50);
        myTree.insert(60);
        myTree.insert(80);
        myTree.insert(20);
        myTree.insert(70);
        myTree.insert(40);
        myTree.insert(44);
        myTree.insert(10);
        myTree.insert(40);

        myTree.delete(80);
        myTree.delete(10);

        for (Integer data : myTree) {
            System.out.print(data + " ");
        }
        System.out.print('\n');
        myTree.forEach( t-> System.out.print(t + " ") );
        System.out.println('\n');

        myTree.setTraversal(Traversal.INORDER);

        for (Integer data : myTree) {
            System.out.print(data + " ");
        }
        System.out.print('\n');
    }
}
