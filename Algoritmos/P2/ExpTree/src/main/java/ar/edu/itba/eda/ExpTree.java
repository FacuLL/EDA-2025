package ar.edu.itba.eda;

import java.util.Scanner;


public class ExpTree implements ExpressionService {

	private Node root;

	public ExpTree() {
	    System.out.print("Introduzca la expresi�n en notaci�n infija con todos los par�ntesis y blancos: ");

		// token analyzer
	    Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
	    String line= inputScanner.nextLine();
	    inputScanner.close();

	    buildTree(line);
	}

	public ExpTree(String input) {
		buildTree(input);
	}
	
	private void buildTree(String line) {	
		  // space separator among tokens
		  Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");
		  root= new Node(lineScanner);
		  lineScanner.close();
	}
	
	static final class Node {
		private String data;
		private Node left, right;
		
		private Scanner lineScanner;

		public Node(Scanner theLineScanner) {
			lineScanner= theLineScanner;
			
			Node auxi = buildExpression();
			data= auxi.data;
			left= auxi.left;
			right= auxi.right;
			
			if (lineScanner.hasNext() ) 
				throw new RuntimeException("Bad expression");
		}
		
		private Node() 	{
		}
		
	
		private Node buildExpression() {
			Node ret = new Node();
			//Regla 1: E -> ( E op E )
			if(lineScanner.hasNext("\\(") ) {
				lineScanner.next();
				ret.left= buildExpression();

				//Operator
				if (!lineScanner.hasNext())
					throw new OperandException("No operands found");

				ret.data = lineScanner.next();
				if (!Utils.isOperator(ret.data))
					throw new OperandException("Invalid operator");

				//Subexpresión
				ret.right = buildExpression();

				//Esperamos un )
				if (lineScanner.hasNext("\\)"))
					lineScanner.next();	//Lo consumimos
				else
					throw new IncorrectParenthesisException("Invalid parenthesis");

				return ret;
			}

			//Regla 2: E -> cte
			if (!lineScanner.hasNext())
				throw new OperandException("Missing operand");

			ret.data = lineScanner.next();
			if (!Utils.isConstant(ret.data))
				throw new OperandException("Invalid operand %s".formatted(ret.data));

			return ret;
		}



	}  // end Node class

	private double evalRec(Node node) {
		if (Utils.isConstant(node.data)) return Double.parseDouble(node.data);
		if (!Utils.isOperator(node.data)) throw new IllegalArgumentException("Invalid operator");
        return switch (node.data) {
            case "+" -> evalRec(node.left) + evalRec(node.right);
            case "*" -> evalRec(node.left) * evalRec(node.right);
            case "/" -> evalRec(node.left) / evalRec(node.right);
            case "-" -> evalRec(node.left) - evalRec(node.right);
            case "^" -> Math.pow(evalRec(node.left), evalRec(node.right));
            default -> throw new IllegalArgumentException("Invalid operator");
        };
    }

	@Override
	public double eval() {
		return evalRec(root);
	}

	private void preorderRec(Node node) {
		if (node == null) return;
		System.out.print(node.data + " ");
		preorderRec(node.left);
		preorderRec(node.right);
	}

	@Override
	public void preorder() {
		preorderRec(root);
		System.out.print("\n");
	}

	private void inorderRec(Node node) {
		if (node == null) return;
		inorderRec(node.left);
		System.out.print(node.data + " ");
		inorderRec(node.right);
	}

	@Override
	public void inorder() {
		inorderRec(root);
		System.out.print("\n");
	}

	private void postorderRec(Node node) {
		if (node == null) return;
		postorderRec(node.left);
		postorderRec(node.right);
		System.out.print(node.data + " ");
	}

	@Override
	public void postorder() {
		postorderRec(root);
		System.out.print("\n");
	}

	// hasta que armen los testeos
	public static void main(String[] args) {
		ExpressionService myExp = new ExpTree();
		System.out.println(myExp);
		myExp.preorder();
		myExp.inorder();
		myExp.postorder();

		System.out.println("Second Tree Test: ");
		ExpressionService myExp2 = new ExpTree("(  (  2 + 3.5 ) * -10 )\n");
		System.out.println(myExp2.eval());
	}

}  // end ExpTree class
