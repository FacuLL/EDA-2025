package ar.edu.itba.eda;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Levenshtein {
	private int[][] matrix;
	private char[] horizontalWord;
	private char[] verticalWord;
	public Levenshtein(char[] horizontalWord, char[] verticalWord) {
		this.horizontalWord= horizontalWord;
		this.verticalWord= verticalWord;
		matrix = new int[ verticalWord.length+1][ horizontalWord.length + 1];
		for(int col = 1; col <= horizontalWord.length; col++)
	    {
	        matrix[0][col] = col;
	    }
		for(int row = 1; row <= verticalWord.length; row++)
	    {
	        matrix[row][0] = row;        
	    }
	}
	public int distance() {
		// no se ha calculado antes
		if (verticalWord != null) {
			// calcular
			for(int row=1; row<= verticalWord.length; row++)
			{
				for(int col=1; col<= horizontalWord.length; col++) {
					matrix[row][col] = Math.min(matrix[row-1][col-1] + (( verticalWord[row-1]== horizontalWord[col-1])?0:1) ,
	                					           Math.min( matrix[row-1][col]+1, matrix[row][col-1]+1 ));
				}                
			}    
			// no las necesito mas. Las destruyo
			verticalWord= null;
			horizontalWord= null;
		}
		return matrix [ matrix.length-1][ matrix[0].length-1] ;  
	}

	public ArrayList<Character> getOperations() {
		ArrayList<Character> result = new ArrayList<>();
		Stack<Character> stack = new Stack<>();
		if (matrix.length == 0 || matrix[0].length == 0) return result;
		distance();
		int actualX = matrix[0].length - 1;
		int actualY = matrix.length - 1;
		while (actualX > 0 || actualY > 0) {
			int actualValue = matrix[actualY][actualX], auxValue;
			if (actualY > 0 && actualValue == matrix[actualY-1][actualX] + 1) {
				stack.push('I');
				actualY--;
			} else if (actualX > 0 && actualValue == matrix[actualY][actualX-1] + 1) {
				stack.push('D');
				actualX--;
			} else if (actualValue == (auxValue = matrix[actualY-1][actualX-1])) {
				stack.push('-');
				actualX--;
				actualY--;
			} else if (actualValue == auxValue + 1) {
				stack.push('S');
				actualX--;
				actualY--;
			} else {
				throw new RuntimeException();
			}
		}
		while(!stack.isEmpty()) result.add(stack.pop());
		return result;
	}

	public static void main(String[] args) {
		String p1;
		String p2;
		Levenshtein l;
		p1= "exkusa";
		p2= "ex-amigo";
		l = new Levenshtein(p1.toCharArray(), p2.toCharArray());
		System.out.println(String.format("las operaciones a realizar para transformar \"%s\" en \"%s\" son:", p1, p2 ) );
		System.out.println( l.getOperations() );
	}
}
