package Excpetions;

public class InvalidNameRuntimeException extends RuntimeException {
    public InvalidNameRuntimeException(String message) {
        super(message);
    }
}