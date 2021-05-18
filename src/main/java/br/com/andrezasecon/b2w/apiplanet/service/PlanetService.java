package br.com.andrezasecon.b2w.apiplanet.service;

import br.com.andrezasecon.b2w.apiplanet.domain.Planet;
import br.com.andrezasecon.b2w.apiplanet.dto.PlanetDTO;

import java.util.List;
import java.util.Optional;

public interface PlanetService {

    // Buscar todos os planetas
    List<PlanetDTO> findAllPlanets();

    // Buscar planeta pelo id
    PlanetDTO findPlanetById(String id);

    // Buscar planeta pelo nome
    List<PlanetDTO> findByNameIgnoreCase(String name);

    // Inserir um planeta
    PlanetDTO insertPlanet(PlanetDTO objPlanet);

    // deletar um planeta pelo id
    void deletePlanet(String id);

}
