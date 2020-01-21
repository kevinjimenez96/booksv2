package dev.kevinjimenez.bookv2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ValueNotFoundException extends RuntimeException {
    public ValueNotFoundException(String messagge){
        super(messagge);
    }
}
