package flopezllopis;

public class SingleLinkedList {

    private Node root;


    private final class Node {
        private int data;
        private Node next;

        private Node( int data) {
            this.data = data;
        }

        private Node( int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public void addFirst( int data ){
        root = new Node( data, root );
    }

    public void print(  ){
        Node n = root;
        while ( n != null ){
            System.out.println( n.data );
            n = n.next;
        }
    }

    public void reverseFirst( int num ) {
        Node actual = root;
        Node siguiente = actual.next;
        while(num > 1 && siguiente != null) {
            Node aux = siguiente.next;
            siguiente.next = actual;
            actual = siguiente;
            siguiente = aux;
            num--;
        }
        root.next = siguiente;
        root = actual;
    }


     public static void mainA(String[] args) {
        SingleLinkedList l = new SingleLinkedList();

       
        l.addFirst(10);
        l.addFirst(28);
        l.addFirst(30);
        l.addFirst(12);
        l.addFirst(50);
        
        System.out.println("ORIGINAL");
        l.print();
        System.out.println("REVERSED FIRST");
        l.reverseFirst(3);
        l.print();
    }

    public static void mainB(String[] args) {
        SingleLinkedList l = new SingleLinkedList();

        l.addFirst(5);
        l.addFirst(4);
        l.addFirst(3);
        l.addFirst(2);
        l.addFirst(1);
        System.out.println("ORIGINAL");
        l.print();
        System.out.println("REVERSED FIRST");
        l.reverseFirst(5);
        l.print();
    }


    public static void mainC(String[] args) {
        SingleLinkedList l = new SingleLinkedList();

        l.addFirst(25);
        l.addFirst(13);
        l.addFirst(25);
        l.addFirst(12);
        l.addFirst(16);
       
       
        
       
        System.out.println("ORIGINAL");
        l.print();
        System.out.println("REVERSED FIRST");
        l.reverseFirst(1);
        l.print();
    }

    public static void mainD(String[] args) {
        SingleLinkedList l = new SingleLinkedList();

        l.addFirst(10);
        l.addFirst(28);
        l.addFirst(30);
        l.addFirst(12);
        l.addFirst(50);
        
        System.out.println("ORIGINAL");
        l.print();
        System.out.println("REVERSED FIRST");
        l.reverseFirst(33);
        l.print();
    }
}
