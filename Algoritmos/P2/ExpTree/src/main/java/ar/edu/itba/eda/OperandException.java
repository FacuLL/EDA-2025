package ar.edu.itba.eda;
public class OperandException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OperandException(String message) {
        super(message);
    }
}