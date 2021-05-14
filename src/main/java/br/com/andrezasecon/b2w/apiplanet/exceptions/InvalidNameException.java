package br.com.andrezasecon.b2w.apiplanet.exceptions;

public class InvalidNameException extends ValidationException {


    public InvalidNameException() {
        super("Planet name cannot be empty");
    }
}
