package br.com.andrezasecon.b2w.apiplanet.repository;

import br.com.andrezasecon.b2w.apiplanet.domain.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlanetRepository extends MongoRepository<Planet, String> {

    List<Planet> findByNameIgnoreCase(String name);
}
