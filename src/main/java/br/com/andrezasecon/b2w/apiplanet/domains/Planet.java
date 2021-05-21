package br.com.andrezasecon.b2w.apiplanet.domains;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

@Document(collection = "planets") //nome da collection no banco MongoDB
public class Planet implements Serializable {
    public static final long serialVersionUID = 1L;

    @Transient
    public static final String SEQUENCE_NAME = "planet_sequence";

    @Indexed
    @Field("idPlanet")
    private long idPlanet;

    @Id
    private String id;
    private String name;
    private String climate;
    private String terrain;
    private Integer filmsAppearances = 0;

    public Planet() {
    }

    public Planet(String name, String climate, String terrain) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
    }

    public Planet(long idPlanet, String name, String climate, String terrain) {
        this.idPlanet = idPlanet;
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
    }

    public Planet(Long idPlanet, String id, String name, String climate, String terrain, Integer filmsAppearances) {
        this.idPlanet = idPlanet;
        this.id = id;
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

    public String getId(){return  this.id;}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(id, planet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
