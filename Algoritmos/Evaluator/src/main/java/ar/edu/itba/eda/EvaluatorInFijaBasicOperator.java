package ar.edu.itba.eda;

import java.util.*;

public class EvaluatorInFijaBasicOperator {

    private Scanner scannerLine;

    private final Map<String, Double> variables;

    private static final boolean[][] precedenceMatriz=
            {       { true,  true,  false, false, false, false, true },
                    { true,  true,  false, false, false, false, true },
                    { true,  true,  true,  true, false, false, true  },
                    { true,  true,  true,  true, false, false, true },
                    { true,  true,  true,  true, false, false, true },
                    { false,  false,  false,  false, false, false, false },
                    { false,  false,  false,  false, false, false, false } // da igual
            };


    public EvaluatorInFijaBasicOperator(Map<String, Double> variables)  {
        this.variables = variables;
        Scanner input = new Scanner(System.in).useDelimiter("\\n");
        System.out.print("Introduzca la expresiÃ³n en notaciÃ³n infija: ");
        String line= input.nextLine();
        input.close();

        scannerLine = new Scanner(line).useDelimiter("\\s+");
    }

    public EvaluatorInFijaBasicOperator() {
        this(new HashMap<>());
    }

    public Double evaluate()
    {
        String exp = infijaToPostfija();
        scannerLine = new Scanner(exp).useDelimiter("\\s+");
        Stack<Double> aux = new Stack<>();

        while( scannerLine.hasNext() )
        {
            String token = scannerLine.next();
            Operation operation = Operation.getFromSymbol(token);
            if (operation == null) {
                if (variables.containsKey(token)) aux.push(variables.get(token));
                else aux.push(Double.parseDouble(token));
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

    private boolean hasPrecedence(Operation actual, Operation previous) {
        return precedenceMatriz[previous.ordinal()][actual.ordinal()];
    }

    private boolean isPrintable(Operation op) {
        return !op.equals(Operation.OPEN_PAR) && !op.equals(Operation.CLOSE_PAR);
    }

    private String infijaToPostfija()
    {
        StringBuilder postfija = new StringBuilder();
        Stack<Operation> aux = new Stack<>();

        while( scannerLine.hasNext() ) {
            String token = scannerLine.next();
            Operation operation = Operation.getFromSymbol(token);
            if (operation == null) {
                postfija.append(token).append(" ");
            } else {
                while (!aux.isEmpty() && hasPrecedence(operation, aux.peek())) {
                    Operation previous = aux.pop();
                    if (isPrintable(previous)) postfija.append(previous).append(" ");
                }
                if (operation.equals(Operation.CLOSE_PAR) && aux.peek().equals(Operation.OPEN_PAR))
                    aux.pop();
                else
                    aux.push(operation);
            }
        }

        while (!aux.isEmpty()) {
            Operation previous = aux.pop();
            if (isPrintable(previous)) postfija.append(previous).append(" ");
        }

        System.out.println("Postfija= " + postfija);
        return postfija.toString();
    }




    public static void main(String[] args) {

        EvaluatorInFijaBasicOperator e = new EvaluatorInFijaBasicOperator(new HashMap<>()
        { {
            put("v1", 12.3);
            put("v3", 5.0);
        } });
        System.out.println(e.evaluate());


    }
}
