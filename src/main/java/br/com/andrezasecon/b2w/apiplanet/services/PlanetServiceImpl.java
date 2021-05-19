package br.com.andrezasecon.b2w.apiplanet.services;

import br.com.andrezasecon.b2w.apiplanet.domains.Planet;
import br.com.andrezasecon.b2w.apiplanet.dto.PlanetDTO;
import br.com.andrezasecon.b2w.apiplanet.repositories.PlanetRepository;
import br.com.andrezasecon.b2w.apiplanet.services.exceptions.ResourceAlreadyExistException;
import br.com.andrezasecon.b2w.apiplanet.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanetServiceImpl implements PlanetService {

    private static final Logger logger = LoggerFactory.getLogger(PlanetServiceImpl.class);

    @Autowired
    private PlanetRepository planetRepository;

    @Override
    public List<PlanetDTO> findAllPlanets() {
        List<Planet> list = planetRepository.findAll();
        if(list.isEmpty()){
            logger.info("Planets not found");
            throw new ResourceNotFoundException("Planets not found");
        }else {
            logger.info("Planets found");
            return list.stream().map(x -> new PlanetDTO(x)).collect(Collectors.toList());
        }
    }

    @Override
    public PlanetDTO findPlanetById(String id) {
        Optional<Planet> obj = planetRepository.findById(id);
        Planet plan = obj.orElseThrow(() -> {
            logger.info("Planet id not found");
              return   new ResourceNotFoundException("Planet id " + id + " not found");});
        logger.info("Planet id found");
        return new PlanetDTO(plan);
    }

    @Override
    public List<PlanetDTO> findByNameIgnoreCase(String name) {
        List<Planet> list = planetRepository.findByNameIgnoreCase(name);
        if (list.isEmpty()) {
            logger.info("Planet name not found");
            throw new ResourceNotFoundException("Name " + name + " not found");
        } else {
            logger.info("Planet name found");
            return list.stream().map(x -> new PlanetDTO(x)).collect(Collectors.toList());
        }
    }

    @Override
    public PlanetDTO insertPlanet(PlanetDTO objPlanet) {
        List<Planet> exist = planetRepository.findByNameIgnoreCase(objPlanet.getName());
        if(!exist.isEmpty()){
            logger.info("Planet already exist");
            throw new ResourceAlreadyExistException("Planet Already exist");
        }
        Planet entity = new Planet();
            entity.setName(objPlanet.getName());
            entity.setClimate(objPlanet.getClimate());
            entity.setTerrain(objPlanet.getTerrain());
            entity = planetRepository.save(entity);
            logger.info("Planet inserted");
            return new PlanetDTO(entity);
    }

    @Override
    public void deletePlanet(String id) {
        Optional<Planet> obj = planetRepository.findById(id);
        if(obj.isPresent()) {
            logger.info("Planet deleted");
            planetRepository.deleteById(id);

        }else {
            logger.info("Planet id dont exist");
            throw new ResourceNotFoundException("Planet id dont exist");
        }
    }

}