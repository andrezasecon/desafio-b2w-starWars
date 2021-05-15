package br.com.andrezasecon.b2w.apiplanet.exceptions;


public class NotFoundNameException extends ValidationException {
    public static final long serialVersionUID = 1L;

    public NotFoundNameException(String name) {

        super(String.format("Planet with name %s not found", name));
    }
}
