package ar.edu.itba.eda;

import java.util.Stack;

public class StringEvaluator {

    public String evaluate(String query) {
        String[] tokens = query.split(" ");
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            StringOperation operation = StringOperation.fromSymbol(token);
            if (operation == null) stack.push(token);
            else {
                if (stack.isEmpty()) throw new RuntimeException("Falta operando");
                String op2 = stack.pop();
                if (stack.isEmpty()) throw new RuntimeException("Falta operando");
                String op1 = stack.pop();
                stack.push(operation.apply(op1, op2));
            }
        }
        if (stack.isEmpty()) throw new RuntimeException("Falta resultado");
        String result = stack.pop();
        if (!stack.isEmpty()) throw new RuntimeException("Falta operador");
        return result;
    }

}
