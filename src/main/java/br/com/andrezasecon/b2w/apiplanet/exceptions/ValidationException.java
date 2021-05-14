package br.com.andrezasecon.b2w.apiplanet.exceptions;

import lombok.Getter;

public class ValidationException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    @Getter
    String error;

    public ValidationException(String error) {
        super(error);
        this.error = error;
    }
}