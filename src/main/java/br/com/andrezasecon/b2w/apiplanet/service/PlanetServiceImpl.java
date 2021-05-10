package br.com.andrezasecon.b2w.apiplanet.service;

import br.com.andrezasecon.b2w.apiplanet.domain.Planet;
import br.com.andrezasecon.b2w.apiplanet.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("PlanetServiceV1")
public class PlanetServiceImpl implements PlanetService
{

    @Autowired
    private PlanetRepository planetRepository;



    public List<Planet> findAllPlanets() {
        return planetRepository.findAll();

    }

    public Optional<Planet> findPlanetById(String id) {
        //ṕkoiuyuftgyuio
        return planetRepository.findById(id);
    }

    public List<Planet> findPlanetByName(String name) {
        return planetRepository.findByNameIgnoreCase(name);
    }

    public Planet insertPlanet(Planet objPlanet) {
       return planetRepository.insert(objPlanet);
    }


    public void deletePlanet(String id) {
        planetRepository.deleteById(id);
    }
}
