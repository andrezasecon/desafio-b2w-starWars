package br.com.andrezasecon.b2w.apiplanet.services;

import br.com.andrezasecon.b2w.apiplanet.domains.Planet;
import br.com.andrezasecon.b2w.apiplanet.dto.PlanetDTO;
import br.com.andrezasecon.b2w.apiplanet.repositories.PlanetRepository;
import br.com.andrezasecon.b2w.apiplanet.services.exceptions.ResourceAlreadyExistException;
import br.com.andrezasecon.b2w.apiplanet.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanetServiceImpl implements PlanetService {

    private static final Logger logger = LoggerFactory.getLogger(PlanetServiceImpl.class);

    @Autowired
    private PlanetRepository planetRepository;

    @Override
    public Page<PlanetDTO> findAllPlanetsPaged(Pageable pageable) {
        Page<Planet> list = planetRepository.findAll(pageable);
        if (list.isEmpty()) {
            logger.info("Planets not found");
            throw new ResourceNotFoundException("Planets not found");
        } else {
            logger.info("Planets found");
            return list.map(x -> new PlanetDTO(x));
        }
    }

    @Override
    public List<PlanetDTO> findPlanetById(Long idPlanet) {
        List<Planet> list = planetRepository.findPlanetByIdPlanet(idPlanet);
        if (list.isEmpty()) {
            logger.info("Planet id not found");
            throw new ResourceNotFoundException("Planet id " + idPlanet + " not found");
        } else {
            logger.info("Planet id found");
            return list.stream().map(x -> new PlanetDTO(x)).collect(Collectors.toList());
        }
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
        if (!exist.isEmpty()) {
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
    public void deletePlanet(Long idPlanet) {
        List<Planet> list = planetRepository.findPlanetByIdPlanet(idPlanet);

        if (!list.isEmpty()) {
            logger.info("Planet deleted");
            list.forEach(x -> {
                planetRepository.deleteById(x.getId());
            });

        } else {
            logger.info("Planet id dont exist");
            throw new ResourceNotFoundException("Planet id dont exist");
        }
    }

}