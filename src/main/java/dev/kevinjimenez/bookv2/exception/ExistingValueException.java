package dev.kevinjimenez.bookv2.exception;

public class ExistingValueException extends  RuntimeException{
    public ExistingValueException(String message) {
        super(message);
    }
}
