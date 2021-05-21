package br.com.andrezasecon.b2w.apiplanet.dto;

import br.com.andrezasecon.b2w.apiplanet.domains.Planet;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PlanetDTO {

    @Indexed
    private long idPlanet;
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
        this.idPlanet = planet.getIdPlanet();
        this.name = planet.getName();
        this.climate = planet.getClimate();
        this.terrain = planet.getTerrain();
        this.filmsAppearances = planet.getFilmsAppearances();
    }

    public PlanetDTO(Long idPlanet, String name, String climate, String terrain, Integer filmsAppearances) {
        this.idPlanet = idPlanet;
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.filmsAppearances = filmsAppearances;
    }

    public long getIdPlanet() {
        return idPlanet;
    }

    public void setIdPlanet(long idPlanet) {
        this.idPlanet = idPlanet;
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
