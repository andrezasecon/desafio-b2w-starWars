package br.com.andrezasecon.b2w.apiplanet.service;

import br.com.andrezasecon.b2w.apiplanet.domain.Planet;
import br.com.andrezasecon.b2w.apiplanet.dto.PlanetDTO;
import br.com.andrezasecon.b2w.apiplanet.repository.PlanetRepository;
import br.com.andrezasecon.b2w.apiplanet.service.exceptions.DataBaseException;
import br.com.andrezasecon.b2w.apiplanet.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    private PlanetRepository planetRepository;

    @Override
    public List<PlanetDTO> findAllPlanets() {
        List<Planet> list = planetRepository.findAll();
        return list.stream().map(x -> new PlanetDTO(x)).collect(Collectors.toList());
    }

    @Override
    public PlanetDTO findPlanetById(String id) {
        Optional<Planet> obj = planetRepository.findById(id);
        Planet plan = obj.orElseThrow(() -> new ResourceNotFoundException("Planet id " + id + " not found"));
        return new PlanetDTO(plan);
    }

    @Override
    public List<PlanetDTO> findByNameIgnoreCase(String name) {
        List<Planet> list = planetRepository.findByNameIgnoreCase(name);
        return list.stream().map(x -> new PlanetDTO(x)).collect(Collectors.toList());
    }

    @Override
    public PlanetDTO insertPlanet(PlanetDTO objPlanet) {
        Planet entity = new Planet();
        entity.setName(objPlanet.getName());
        entity.setClimate(objPlanet.getClimate());
        entity.setTerrain(objPlanet.getTerrain());
        entity = planetRepository.save(entity);
        return new PlanetDTO(entity);
    }

    @Override
    public void deletePlanet(String id) {
        try {
            planetRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id " + id + " not found");
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation");
        }
    }
}