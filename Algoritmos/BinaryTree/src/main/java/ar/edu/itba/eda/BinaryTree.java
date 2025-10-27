package ar.edu.itba.eda;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.UnaryOperator;


public class BinaryTree implements BinaryTreeService<String> {
	
	private Node root;

	private Scanner inputScanner;
    private int identation;

    public BinaryTree(String fileName) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, FileNotFoundException {
		 InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);

		 if (is == null)
			 throw new FileNotFoundException(fileName);
		 
		 inputScanner = new Scanner(is);
		 inputScanner.useDelimiter("\\s+");

		 buildTree();
		
		 inputScanner.close();
	}
	

	
	private void buildTree() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {	
	
		 Queue<NodeHelper> pendingOps= new LinkedList<>();
		 String token;
		 
		 root= new Node();
		 pendingOps.add(new NodeHelper(root, node -> node));
		 
		 while(inputScanner.hasNext()) {
			 
			 token= inputScanner.next();

			 NodeHelper aPendingOp = pendingOps.remove();
			 Node currentNode = aPendingOp.getNode();
			 
			 if ( token.equals("?") ) {
				 // no hace falta poner en null al L o R porque ya esta con null
				 
			 // reservar el espacio en Queue aunque NULL no tiene hijos para aparear
				pendingOps.add( new NodeHelper(null, node -> node) );  // como si hubiera izq
				pendingOps.add( new NodeHelper(null, node -> node) );  // como si hubiera der
			 }
			 else {
				 if (currentNode != null) {
					 currentNode = aPendingOp.getAction().apply(currentNode);
					 currentNode.data = token;
				 }
				 // hijos se postergan
				 pendingOps.add(new NodeHelper(currentNode, current ->
					 current.setLeftTree(new Node())
				 ));
				 pendingOps.add(new NodeHelper(currentNode, current ->
					 current.setRightTree(new Node())
				 ));
			 }
	
				 
		 }
			
		 if (root.data == null)  // no entre al ciclo jamas 
			 root= null;
		 
	 }

 	private void preOrderRec(Node actual) {
		if (actual == null) return;
		System.out.println(actual);
		preOrderRec(actual.left);
		preOrderRec(actual.right);
	}
    
	@Override
	public void preorder() {
		preOrderRec(root);
	}
	
	private void postOrderRec(Node actual) {
		if (actual == null) return;
		postOrderRec(actual.left);
		postOrderRec(actual.right);
		System.out.println(actual);
	}
	
	@Override
	public void postorder() {
		postOrderRec(root);
	}

	private void printLeaf(Node actual, int ident) {
		StringBuilder builder = new StringBuilder();
        builder.append("	".repeat(Math.max(0, ident)));
		builder.append("└── ");
		builder.append((actual == null || actual.data == null) ? "null" : actual.data);
		System.out.println(builder);
		if (actual != null && !actual.isLeaf()) {
			printLeaf(actual.left, ident+1);
			printLeaf(actual.right, ident+1);
		}
	}

	public void printHierarchy() {
		printLeaf(root, 0);
	}


	// hasta el get() no se evalua
	class Node {
		private String data;
		private Node left;
		private Node right;
		
		public Node setLeftTree(Node aNode) {
			left= aNode;
			return left;
		}
		
		
		public Node setRightTree(Node aNode) {
			right= aNode;
			return right;
		}

		public Node() {}

		public Node(String data) {
			this.data = data;
		}

		private boolean isLeaf() {
			return left == null && right == null;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Node node)) return false;
			return node.data.equals(data) && node.right.equals(right) && node.left.equals(left);
		}
	}  // end Node class

	
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

	private Integer getHeight(Node actual) {
		if (actual.isLeaf()) return 1;
		return Math.max(getHeight(actual.left), getHeight(actual.right)) + 1;
	}

	public Integer getHeight() {
		return getHeight(root);
	}

	private String toFileString() {
		Integer height = getHeight();
		Queue<Node> actual = new LinkedList<>();
		actual.add(root);
		StringBuilder output = new StringBuilder();
		while (height != 0) {
			for (int i = 0; i < Math.pow(2, height); i++) {
				Node current = actual.remove();
				output.append(current.data);
				actual.add(current.left);
				actual.add(current.right);
			}
			height--;
		}
		return output.toString();
	}

	public void toFile(String fileName) throws IOException, URISyntaxException {
		OutputStream os;
		URL resourceUrl = getClass().getResource(fileName);
		File file = new File(resourceUrl.toURI());
		os = new FileOutputStream(file);
		os.write(toFileString().getBytes(StandardCharsets.UTF_8));
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof BinaryTree tree)) return false;
		return tree.root.equals(root);
	}

	public static void main(String[] args) throws FileNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		BinaryTreeService rta = new BinaryTree("data1_1");
		rta.printHierarchy();

		try {
			rta.toFile("output");
		} catch(Exception e) {
			throw new RuntimeException(e);
		}

		BinaryTreeService otro = new BinaryTree("output");

		System.out.println(otro.equals(rta));

	}

}  