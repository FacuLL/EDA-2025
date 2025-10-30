package ar.edu.itba.eda.core;

public class AVLTree<T extends Comparable<? super T>> implements AVLTreeInterface<T> {
    private Node<T> root;
    private int size;

    @Override
    public void insert(T myData){
        if(myData == null)
            throw new IllegalArgumentException("Data cannot be null");
        root = insert(root, myData);
    }

    private Node<T> insert(Node<T> currentNode, T myData){
        if(currentNode == null)
            return new Node<>(myData);

        if(myData.compareTo(currentNode.data) <= 0)
            currentNode.left = insert(currentNode.left, myData);
        else
            currentNode.right = insert(currentNode.right, myData);

        int i = currentNode.left == null ? -1 : currentNode.left.height;
        int d = currentNode.right == null ? -1 : currentNode.right.height;
        currentNode.height = 1 + Math.max(i, d);
        int balance = getBalance(currentNode);

        if(balance < -1) {
            if(getBalance(currentNode.right) > 0) {
                currentNode.right = rightRotate(currentNode.right);
            }
            return leftRotate(currentNode);
        }
        else if(balance > 1) {
            if(getBalance(currentNode.left) < 0) {
                currentNode.left = leftRotate(currentNode.left);
            }
            return rightRotate(currentNode);
        }

        return currentNode;
    }

    private int getBalance(Node<T> currentNode) {
        if(currentNode == null)
            return 0;
        return height(currentNode.left) - height(currentNode.right);
    }

    private int height(Node<T> currentNode) {
        if (currentNode == null)
            return 0;
        return currentNode.height;
    }

    public int getHeight() {
        return root.height;
    }

    private Node<T> rightRotate(Node<T> currentNode) {
        Node<T> r = currentNode.left;
        currentNode.left = r.right;
        r.right = currentNode;
        currentNode.height = Math.max(height(currentNode.left), height(currentNode.right)) + 1;
        r.height = Math.max(height(r.left), height(r.right)) + 1;
        return r;
    }

    private Node<T> leftRotate(Node<T> currentNode) {
        Node<T> r = currentNode.right;
        currentNode.right = r.left;
        r.left = currentNode;
        currentNode.height = Math.max(height(currentNode.left), height(currentNode.right)) + 1;
        r.height = Math.max(height(r.left), height(r.right)) + 1;
        return r;
    }


    public NodeTreeInterface<T> getRoot() {
        return root;
    }

    // Borrado público
    public void delete(T myData) {
        if (myData == null)
            throw new IllegalArgumentException("Data cannot be null");
        root = delete(root, myData);
    }

    // Implementación recursiva del borrado en AVL
    private Node<T> delete(Node<T> currentNode, T myData) {
        if (currentNode == null)
            return null;

        if (myData.compareTo(currentNode.data) < 0) {
            currentNode.left = delete(currentNode.left, myData);
        } else if (myData.compareTo(currentNode.data) > 0) {
            currentNode.right = delete(currentNode.right, myData);
        } else {
            // Nodo encontrado
            if (currentNode.left == null || currentNode.right == null) {
                Node<T> temp = currentNode.left != null ? currentNode.left : currentNode.right;
                if (temp == null) {
                    // sin hijos
                    return null;
                } else {
                    // un solo hijo
                    return temp;
                }
            } else {
                // dos hijos: obtener sucesor inorder (mínimo en subárbol derecho)
                Node<T> temp = minValueNode(currentNode.right);
                currentNode.data = temp.data;
                currentNode.right = delete(currentNode.right, temp.data);
            }
        }

        // actualizar altura
        int i = currentNode.left == null ? -1 : currentNode.left.height;
        int d = currentNode.right == null ? -1 : currentNode.right.height;
        currentNode.height = 1 + Math.max(i, d);

        int balance = getBalance(currentNode);

        // reequilibrar
        if (balance > 1) {
            if (getBalance(currentNode.left) < 0) {
                currentNode.left = leftRotate(currentNode.left);
            }
            return rightRotate(currentNode);
        }
        if (balance < -1) {
            if (getBalance(currentNode.right) > 0) {
                currentNode.right = rightRotate(currentNode.right);
            }
            return leftRotate(currentNode);
        }

        return currentNode;
    }

    private Node<T> minValueNode(Node<T> node) {
        Node<T> current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    @Override
    public boolean find(T myData) {
        if(root==null)
            return false;
        return root.hasChildren(myData);
    }

    @Override
    public String toString() {
        return root.toString();
    }

    private class Node<T extends Comparable<? super T>> implements NodeTreeInterface<T>{
        private T data;
        private Node<T> left;
        private Node<T> right;
        private int height;

        public Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.height = 1;
        }

        public Node(T data) {
            this(data, null, null);
        }

        @Override
        public T getData() {
            return data;
        }

        @Override
        public NodeTreeInterface<T> getLeft() {
            return left;
        }

        @Override
        public NodeTreeInterface<T> getRight() {
            return right;
        }

        @Override
        public String toString() {
            StringBuilder buffer = new StringBuilder();
            print(buffer, "", "");
            return buffer.toString();
        }

        private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
            buffer.append(prefix);
            buffer.append(data);
            buffer.append('\n');
            if(left!=null)
                left.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
            if(right!=null)
                right.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
        }

        private boolean hasChildren(T myData) {
            if(data.compareTo(myData)==0)
                return true;
            if(data.compareTo(myData)>0 && left!=null)
                return left.hasChildren(myData);
            if(data.compareTo(myData)<0 && right!=null)
                return right.hasChildren(myData);
            return false;
        }

    }

    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();
        avl.insert(1);
        avl.insert(2);
        avl.insert(4);
        avl.insert(7);
        avl.insert(6);
        avl.insert(15);
        avl.insert(3);
        avl.insert(10);
        avl.insert(17);
        avl.insert(19);
        avl.insert(16);
        System.out.println(avl);
    }
}