package br.com.andrezasecon.b2w.apiplanet.service.exceptions;

public class DataBaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DataBaseException(String msg){
        super(msg);
    }
}
