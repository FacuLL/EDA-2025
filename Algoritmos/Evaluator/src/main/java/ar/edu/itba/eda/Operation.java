package ar.edu.itba.eda;

public enum Operation {
    SUM("+") {
        @Override
        public Double apply(Double op1, Double op2) {
            return op1+op2;
        }
    },
    REST("-") {
        @Override
        public Double apply(Double op1, Double op2) {
            return op1-op2;
        }
    },
    MULT("*") {
        @Override
        public Double apply(Double op1, Double op2) {
            return op1*op2;
        }
    },
    DIV("/") {
        @Override
        public Double apply(Double op1, Double op2) {
            return op1/op2;
        }
    },
    POW("^") {
        @Override
        public Double apply(Double op1, Double op2) {
            return Math.pow(op1, op2);
        }
    },
    OPEN_PAR("(") {
        @Override
        public Double apply(Double op1, Double op2) {
            throw new RuntimeException("Cannot apply PARENTESIS");
        }

    },
    CLOSE_PAR(")") {
        @Override
        public Double apply(Double op1, Double op2) {
            throw new RuntimeException("Cannot apply PARENTESIS");
        }
    };


    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public static Operation getFromSymbol(String symbol) {
        for (Operation op : Operation.values()) {
            if (op.symbol.equals(symbol)) return op;
        }
        return null;
    }

    public abstract Double apply(Double op1, Double op2);

    @Override
    public String toString() {
        return symbol;
    }
}
