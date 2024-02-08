package hu.geralt.gradproject.exceptions;

public class DuplicateUsernameException extends RuntimeException {
    public DuplicateUsernameException(String username) {
        super("The username '" + username + "' already exists. Please choose another one.");
    }
}