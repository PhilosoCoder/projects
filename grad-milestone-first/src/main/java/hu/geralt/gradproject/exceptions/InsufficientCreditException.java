package hu.geralt.gradproject.exceptions;

public class InsufficientCreditException extends RuntimeException {

    public InsufficientCreditException() {
        super("You do not have enough credit!");
    }

    public InsufficientCreditException(String message) {
        super(message);
    }
}