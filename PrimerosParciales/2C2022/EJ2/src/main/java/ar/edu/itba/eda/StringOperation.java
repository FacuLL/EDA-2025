package ar.edu.itba.eda;

public enum StringOperation {
    CONCAT("+") {
        @Override
        public String apply(String op1, String op2) {
            return op1 + op2;
        }
    },
    SUBTRACT("-") {
        @Override
        public String apply(String op1, String op2) {
            int indexOf = StringUtils.indexOf(op2, op1);
            if (indexOf != -1) {
                return op1.substring(0, indexOf) + op1.substring(indexOf + op2.length());
            }
            return op1;
        }
    },
    INTERSPERSE("*") {
        @Override
        public String apply(String op1, String op2) {
            char[] c1 = op1.toCharArray();
            char[] c2 = op2.toCharArray();
            int i = 0, j = 0;
            StringBuilder result = new StringBuilder();
            while (i < c1.length || j < c2.length) {
                if (i < op1.length()) result.append(c1[i]);
                if (j < op2.length()) result.append(c2[j]);
                i++;
                j++;
            }
            return result.toString();
        }
    },
    DELETE("/") {
        @Override
        public String apply(String op1, String op2) {
            char[] chars = op1.toCharArray();
            char[] toDelete = op2.toCharArray();
            StringBuilder toReturn = new StringBuilder();
            for (char myChar : chars) {
                if (!StringUtils.contains(myChar, toDelete))
                    toReturn.append(myChar);
            }
            return toReturn.toString();
        }
    },
    SPECIAL_INTERSPERSE("^") {
        @Override
        public String apply(String op1, String op2) {
            StringBuilder toReturn = new StringBuilder();
            for (int i = 0; i < op1.length(); i++) {
                toReturn.append(op2);
                toReturn.append(op1, 0, i+1);
            }
            return toReturn.toString();
        }
    };

    private final String symbol;

    StringOperation(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public abstract String apply(String op1, String op2);

    public static StringOperation fromSymbol(String symbol) {
        for (StringOperation operation: StringOperation.values()) {
            if (operation.symbol.equals(symbol)) return operation;
        }
        return null;
    }
}
