package br.com.andrezasecon.b2w.apiplanet.service;

import br.com.andrezasecon.b2w.apiplanet.domain.Planet;

import java.util.List;
import java.util.Optional;

public interface PlanetService {

    // Buscar todos os planetas
    List<Planet> findAllPlanets();

    // Buscar planeta pelo id
    Optional<Planet> findPlanetById(String id);

    // Buscar planeta pelo nome
    List<Planet> findByNameIgnoreCase(String name);

    // Inserir um planeta
    Planet insertPlanet(Planet objPlanet);

    // deletar um planeta pelo di
    void deletePlanet(String id);

}
