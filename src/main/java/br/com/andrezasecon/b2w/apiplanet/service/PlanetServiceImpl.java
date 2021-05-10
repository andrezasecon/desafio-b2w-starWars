package br.com.andrezasecon.b2w.apiplanet.service;

import br.com.andrezasecon.b2w.apiplanet.domain.Planet;
import br.com.andrezasecon.b2w.apiplanet.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetServiceImpl implements PlanetService
{

    @Autowired
    private PlanetRepository planetRepository;

    @Override
    public List<Planet> findAllPlanets() {
       return planetRepository.findAll();
    }

    @Override
    public Optional<Planet> findPlanetById(String id) {

        return planetRepository.findById(id);
    }

    @Override
    public List<Planet> findByNameIgnoreCase(String name) {

        return planetRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Planet insertPlanet(Planet objPlanet) {

        return planetRepository.insert(objPlanet);
    }

}
