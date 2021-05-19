package br.com.andrezasecon.b2w.apiplanet.domains;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Document(collection = "planets") //nome da collection no banco MongoDB
public class Planet implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private String climate;
    private String terrain;
    private Integer filmsAppearances=0;

    public Planet() {
    }

    public Planet(String id, String name, String climate, String terrain, Integer filmsAppearances) {
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
