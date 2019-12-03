package exception;
public class IncompleteJsonException extends RuntimeException {
    public IncompleteJsonException(String errorMessage) { super(errorMessage)}
}