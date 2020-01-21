package dev.kevinjimenez.bookv2.exception;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String email){
        super("The user '" + email +"' is not valid.");
    }
}
