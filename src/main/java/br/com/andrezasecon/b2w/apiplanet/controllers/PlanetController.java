package br.com.andrezasecon.b2w.apiplanet.controllers;

import br.com.andrezasecon.b2w.apiplanet.client.ClientFeignSwapi;
import br.com.andrezasecon.b2w.apiplanet.client.PlanetSwapiPaginationResponse;
import br.com.andrezasecon.b2w.apiplanet.controllers.doc.PlanetControllerDoc;
import br.com.andrezasecon.b2w.apiplanet.dto.PlanetDTO;
import br.com.andrezasecon.b2w.apiplanet.services.PlanetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Validated
public class PlanetController implements PlanetControllerDoc {

    private static final Logger logger = LoggerFactory.getLogger(PlanetController.class);

    @Autowired
    private PlanetService planetService;

    @Autowired
    private ClientFeignSwapi clientFeignSwapi;

    @GetMapping
    public ResponseEntity<List<PlanetDTO>> findAllPlanets() {
        List<PlanetDTO> planetList = planetService.findAllPlanets();
        planetList.stream().forEach(p -> {
            PlanetSwapiPaginationResponse planetsApi = clientFeignSwapi.getPlanetsByName(p.getName());
            planetsApi.getResults().stream().forEach(swapiPlanet ->
                    p.setFilmsAppearances(swapiPlanet.getFilms().size() + p.getFilmsAppearances())
            );
        });
        return ResponseEntity.ok().body(planetList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<PlanetDTO>> findPlanetById(@PathVariable Long id) {
        List<PlanetDTO> planetList = planetService.findPlanetById(id);
        planetList.stream().forEach(p -> {
            PlanetSwapiPaginationResponse planetsApi = clientFeignSwapi.getPlanetsByName(p.getName());
            planetsApi.getResults().stream().forEach(swapiPlanet ->
                    p.setFilmsAppearances(swapiPlanet.getFilms().size() + p.getFilmsAppearances())
            );
        });
        return ResponseEntity.ok().body(planetList);
    }

    @GetMapping(value = "/find/{name}")
    public ResponseEntity<List<PlanetDTO>> findPlanetByName(@PathVariable String name) {
        List<PlanetDTO> planetList = planetService.findByNameIgnoreCase(name);
        planetList.stream().forEach(p -> {
            PlanetSwapiPaginationResponse planetsApi = clientFeignSwapi.getPlanetsByName(p.getName());
            planetsApi.getResults().stream().forEach(swapiPlanet ->
                    p.setFilmsAppearances(swapiPlanet.getFilms().size() + p.getFilmsAppearances())
            );
        });
        return ResponseEntity.ok().body(planetList);
    }

    @PostMapping
    public ResponseEntity<PlanetDTO> insertPlanet(@RequestBody @Valid PlanetDTO pdto) {
        pdto = planetService.insertPlanet(pdto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(pdto.getIdPlanet()).toUri();
        return ResponseEntity.created(uri).body(pdto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PlanetDTO> deletePlanet(@PathVariable Long id) {
        planetService.deletePlanet(id);
        return ResponseEntity.noContent().build();
    }
}
