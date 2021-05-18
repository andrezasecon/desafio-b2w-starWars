package br.com.andrezasecon.b2w.apiplanet.dto;

import br.com.andrezasecon.b2w.apiplanet.domain.SwapiPlanet;

import java.util.List;

public class SwapiPlanetDTO {

    private String name;
    private List<String> films;

    public SwapiPlanetDTO() {
    }

    public SwapiPlanetDTO(SwapiPlanet swapiPlanet) {
        this.name = swapiPlanet.getName();
        this.films = swapiPlanet.getFilms();
    }

    public SwapiPlanetDTO(String name, List<String> films) {
        this.name = name;
        this.films = films;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }
}
