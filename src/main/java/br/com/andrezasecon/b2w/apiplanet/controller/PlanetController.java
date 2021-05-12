package br.com.andrezasecon.b2w.apiplanet.controller;

import br.com.andrezasecon.b2w.apiplanet.client.ClientFeignSwapi;
import br.com.andrezasecon.b2w.apiplanet.client.PlanetSwapiPaginationResponse;
import br.com.andrezasecon.b2w.apiplanet.domain.Planet;
import br.com.andrezasecon.b2w.apiplanet.service.PlanetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/planetas")
public class PlanetController {

    @Autowired
    private PlanetServiceImpl planetService;

    @Autowired
    private ClientFeignSwapi clientFeignSwapi;

    @GetMapping
    public List<Planet> findAll() {
        List<Planet> planetList = planetService.findAllPlanets();
        planetList.stream().forEach(p -> {
            PlanetSwapiPaginationResponse planetsApi = clientFeignSwapi.getPlanetsByName(p.getName());
            planetsApi.getResults().stream().forEach(pa -> {
                p.setFilmCount(pa.getFilms().size() + p.getFilmCount());
            });
        });
        return planetList;
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Planet> findPlanetById(@PathVariable String id) {
        return planetService.findPlanetById(id).map(record -> {
            PlanetSwapiPaginationResponse planetsApi = clientFeignSwapi.getPlanetsByName(record.getName());
            planetsApi.getResults().stream().forEach(pa -> {
                record.setFilmCount(pa.getFilms().size() + record.getFilmCount());
            });
            return ResponseEntity.ok().body(record);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Busca planeta por nome ignorando maiusculas e minusculas
    @GetMapping(value = "/find/{name}")
    public List<Planet> findPlanetByName(@PathVariable String name) {
        List<Planet> planetList = planetService.findByNameIgnoreCase(name);
        planetList.stream().forEach(p -> {
            PlanetSwapiPaginationResponse planetsApi = clientFeignSwapi.getPlanetsByName(p.getName());
            planetsApi.getResults().stream().forEach(pa -> {
                p.setFilmCount(pa.getFilms().size() + p.getFilmCount());
            });
        });
        return planetList;
    }

    @PostMapping
    public void insertPlanet(@RequestBody Planet objPlanet, HttpServletResponse response) {
        planetService.insertPlanet(objPlanet);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

}
