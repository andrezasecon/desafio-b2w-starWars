package br.com.andrezasecon.b2w.apiplanet.service;

import br.com.andrezasecon.b2w.apiplanet.domain.Planet;
import br.com.andrezasecon.b2w.apiplanet.dto.PlanetDTO;

import java.util.List;
import java.util.Optional;


public interface PlanetService {

    // Buscar todos os planetas
    List<Planet> findAllPlanets();
    // Buscar planeta pelo id
    Optional<Planet> findPlanetById(String id);
    // Buscar planeta pelo nome
    List<Planet> findPlanetByName(String name);
    // Inserir um planeta
    Planet insertPlanet(Planet objPlanet);
    // Criar uma cópia do objeto Planeta para PlanetaDto
    Planet fromDTO(PlanetDTO objDTO);

}
