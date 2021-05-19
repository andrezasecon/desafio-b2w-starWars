package br.com.andrezasecon.b2w.apiplanet.domains;


import java.util.List;

public class SwapiPlanet {

    private String name;
    private List<String> films;

    public SwapiPlanet() {
    }

    public SwapiPlanet(String name, List<String> films) {
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