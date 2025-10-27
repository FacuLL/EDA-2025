package ar.edu.itba.eda;

import java.util.Scanner;
import java.util.Stack;

public class EvaluatorPostFija {
    private final Scanner scannerLine;

    public EvaluatorPostFija()
    {
        Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
        System.out.print("Introduzca la expresiOn en notaciOn postfija: ");
        inputScanner.hasNextLine();

        String line = inputScanner.nextLine();

        scannerLine = new Scanner(line).useDelimiter("\\s+");
    }

    public Double evaluate()
    {
        Stack<Double> aux = new Stack<Double>();

        while( scannerLine.hasNext() )
        {
            String token = scannerLine.next();
            Operation operation = Operation.getFromSymbol(token);
            if (operation == null) {
                aux.push(Double.parseDouble(token));
            } else {
                if (aux.isEmpty()) throw new RuntimeException("Faltan operandos");
                Double op2 = aux.pop();
                if (aux.isEmpty()) throw new RuntimeException("Faltan operandos");
                Double op1 = aux.pop();
                Double result = operation.apply(op1, op2);
                aux.push(result);
            }
        }

        Double result = aux.pop();
        if (!aux.isEmpty()) throw new RuntimeException("Faltan operadores");
        return result;
    }

    public static void main(String[] args) {
        Double rta = new EvaluatorPostFija().evaluate();
        System.out.println(rta);
    }


}