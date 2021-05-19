package br.com.andrezasecon.b2w.apiplanet.dto;

import br.com.andrezasecon.b2w.apiplanet.domains.Planet;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PlanetDTO {


    private String id;
    @NotNull(message = "Enter a planet name")
    @NotEmpty(message = "Field name cannot be empty")
    private String name;
    @NotNull(message = "Enter a Climate")
    @NotEmpty(message = "Field Climate cannot be empty")
    private String climate;
    @NotNull(message = "Enter a terrain")
    @NotEmpty(message = "Field terrain cannot be empty")
    private String terrain;
    private Integer filmsAppearances = 0;

    public PlanetDTO() {
    }

    public PlanetDTO(Planet planet) {
        this.id = planet.getId();
        this.name = planet.getName();
        this.climate = planet.getClimate();
        this.terrain = planet.getTerrain();
        this.filmsAppearances = planet.getFilmsAppearances();
    }

    public PlanetDTO(String id, String name, String climate, String terrain, Integer filmsAppearances) {
        this.id = id;
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.filmsAppearances = filmsAppearances;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public Integer getFilmsAppearances() {
        return filmsAppearances;
    }

    public void setFilmsAppearances(Integer filmsAppearances) {
        this.filmsAppearances = filmsAppearances;
    }
}
