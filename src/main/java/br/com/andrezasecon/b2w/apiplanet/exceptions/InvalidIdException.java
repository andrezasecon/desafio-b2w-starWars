package br.com.andrezasecon.b2w.apiplanet.exceptions;

public class InvalidIdException extends ValidationException {

    public InvalidIdException() {
        super("Id cannot be empty");
    }
}
