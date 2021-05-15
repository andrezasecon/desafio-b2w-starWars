package br.com.andrezasecon.b2w.apiplanet.exceptions;

public class AlreadyExistException extends ValidationException {
    public AlreadyExistException(String name) {
        super(String.format("%s planet already exist", name));
    }
}
