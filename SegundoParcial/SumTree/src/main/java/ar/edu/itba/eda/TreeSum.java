package ar.edu.itba.eda;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class TreeSum {

    private Node root;

    private Scanner inputScanner;

    private Integer numNodes;

    public TreeSum(int numNodes, String treeStr)  {
        this.numNodes = numNodes;

        inputScanner = new Scanner(treeStr);
        inputScanner.useDelimiter("\\s+");

        buildTree();

        inputScanner.close();
    }

    private void buildTree() {
        Queue<NodeHelper> pendingOps= new LinkedList<>();
        String token;

        root= new Node();
        pendingOps.add(new NodeHelper(root, node -> node));

        while(inputScanner.hasNext()) {

            token= inputScanner.next();

            NodeHelper aPendingOp = pendingOps.remove();
            Node currentNode = aPendingOp.getNode();

            if ( token.equals("?") ) {
                pendingOps.add( new NodeHelper(null, node -> node) );
                pendingOps.add( new NodeHelper(null, node -> node) );
            }
            else {
                if (currentNode != null) {
                    currentNode = aPendingOp.getAction().apply(currentNode);
                    currentNode.data = Integer.parseInt(token);
                }
                // hijos se postergan
                for (int i = 0; i < numNodes; i++) {
                    int finalI = i;
                    pendingOps.add(new NodeHelper(currentNode, current ->
                        current.setChild(finalI, new Node())
                    ));
                }
            }
        }

        if (root.data == null)
            root= null;
    }

    public void printHierarchy(){
        printHierarchy("", root);
    }

    public void printHierarchy(String initial, Node current) {
        if ( current == null) {
            return ;
        }
        System.out.println(initial + "|_______ " + current.data);
        for (Node node : current.nodes) {
            printHierarchy( initial + "          ", node );
        }
    }

    class Node {
        private Integer data;
        private final Node[] nodes = new Node[numNodes];

        public Node setChild(Integer index, Node node) {
            if (index >= numNodes || index < 0) throw new IllegalArgumentException();
            nodes[index] = node;
            return node;
        }
        
        public Node getChild(Integer index) {
            return nodes[index];
        }

        private boolean isLeaf() {
            for (Node node : nodes) {
                if (node != null) return false;
            }
            return true;
        }

    }

    static class NodeHelper {
        private Node aNode;
        private UnaryOperator<Node> anAction;

        public NodeHelper(Node aNode, UnaryOperator<Node> anAction ) {
            this.aNode= aNode;
            this.anAction= anAction;
        }
        public Node getNode() {
            return aNode;
        }
        public UnaryOperator<Node> getAction() {
            return anAction;
        }
    }

    private LinkedList<String> sumLessPathsRec( Node node, int actualSum, int max ) {
        if (node.isLeaf()) {
            LinkedList<String> list = new LinkedList<>();
            list.add(node.data.toString());
            return list;
        }
        return Arrays.stream(node.nodes)
            .filter(Objects::nonNull)
            .filter(child -> actualSum + node.data + child.data < max)
            .map(child -> sumLessPathsRec(child, actualSum + node.data, max))
            .flatMap(LinkedList::stream)
            .map(string -> node.data + " " + string)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    LinkedList<String> sumLessPaths( int sum ){
        return sumLessPathsRec(root, 0, sum);
    }

    public static void main1(String[] args){
        TreeSum rta = new TreeSum(3,"3 1 2 3 4 8 ? ? ? ? 3 1 30 1");
        rta.printHierarchy();
        LinkedList<String> res = rta.sumLessPaths(10);
        for ( String s: res )
            System.out.println(s);
    }

    public static void main2(String[] args){
        TreeSum rta = new TreeSum(2,"3 1 3 4 8 3 1 ? ? 2 ? 1 ? 0");
        rta.printHierarchy();
        LinkedList<String> res = rta.sumLessPaths(8);
        for ( String s: res )
            System.out.println(s);
    }

    public static void main3(String[] args){
        TreeSum rta = new TreeSum(4,"2 1 2 3 3 4 6 ? ? 3 1 30 ? 3 ? 1 ? ? ? 4");
        rta.printHierarchy();
        LinkedList<String> res = rta.sumLessPaths(8);
        for ( String s: res )
            System.out.println(s);
    }

    public static void main4(String[] args){
        TreeSum rta = new TreeSum(5,"1");
        rta.printHierarchy();
        LinkedList<String> res = rta.sumLessPaths(3);
        for ( String s: res )
            System.out.println(s);
    }

    public static void main5(String[] args){
        TreeSum rta = new TreeSum(2,"1 2 3 4 ? 5 6 ? ? ? ? 7 8");
        rta.printHierarchy();
        LinkedList<String> res = rta.sumLessPaths(100);
        for ( String s: res )
            System.out.println(s);
    }

    public static void main(String[] args){
        System.out.println("Ejemplo 1");
        main1(args);
        System.out.println("Ejemplo 2");
        main2(args);
        System.out.println("Ejemplo 3");
        main3(args);
        System.out.println("Ejemplo 4");
        main4(args);
        System.out.println("Ejemplo 5");
        main5(args);
    }


}