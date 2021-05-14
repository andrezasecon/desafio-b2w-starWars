package br.com.andrezasecon.b2w.apiplanet.exceptions;

public class PlanetNotFoundException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    public PlanetNotFoundException(String id) {

        super(String.format("Planet with Id %s not found", id));
    }

}
