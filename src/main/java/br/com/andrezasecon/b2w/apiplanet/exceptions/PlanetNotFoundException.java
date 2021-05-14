package br.com.andrezasecon.b2w.apiplanet.exceptions;

public class PlanetNotFoundException extends RuntimeException {

        public PlanetNotFoundException(String id) {

            super(String.format("Planet with Id %d not found", id));
        }
}
