package br.com.andrezasecon.b2w.apiplanet.services;

import br.com.andrezasecon.b2w.apiplanet.dto.PlanetDTO;

import java.util.List;

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
