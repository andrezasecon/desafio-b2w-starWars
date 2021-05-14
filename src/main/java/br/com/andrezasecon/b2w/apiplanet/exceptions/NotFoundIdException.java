package br.com.andrezasecon.b2w.apiplanet.exceptions;

public class NotFoundIdException extends ValidationException {


    public NotFoundIdException() {

        super("Id not found!");
    }
}
