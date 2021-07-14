package br.com.andrezasecon.b2w.apiplanet.controllers;

import br.com.andrezasecon.b2w.apiplanet.client.ClientFeignSwapi;
import br.com.andrezasecon.b2w.apiplanet.client.PlanetSwapiPaginationResponse;
import br.com.andrezasecon.b2w.apiplanet.controllers.doc.PlanetControllerDoc;
import br.com.andrezasecon.b2w.apiplanet.dto.PlanetDTO;
import br.com.andrezasecon.b2w.apiplanet.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private PlanetService planetService;

    @Autowired
    private ClientFeignSwapi clientFeignSwapi;

    @GetMapping
    public ResponseEntity<Page<PlanetDTO>> findAllPlanetsPaged(Pageable pageable) {
        Page<PlanetDTO> planetList = planetService.findAllPlanetsPaged(pageable);
        planetList.forEach(p -> {
            PlanetSwapiPaginationResponse planetsApi = clientFeignSwapi.getPlanetsByName(p.getName());
            planetsApi.getResults().forEach(swapiPlanet ->
                    p.setFilmsAppearances(swapiPlanet.getFilms().size() + p.getFilmsAppearances())
            );
        });
        return ResponseEntity.ok().body(planetList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<PlanetDTO>> findPlanetById(@PathVariable Long id) {
        List<PlanetDTO> planetList = planetService.findPlanetById(id);
        planetList.forEach(p -> {
            PlanetSwapiPaginationResponse planetsApi = clientFeignSwapi.getPlanetsByName(p.getName());
            planetsApi.getResults().forEach(swapiPlanet ->
                    p.setFilmsAppearances(swapiPlanet.getFilms().size() + p.getFilmsAppearances())
            );
        });
        return ResponseEntity.ok().body(planetList);
    }

    @GetMapping(value = "/find/{name}")
    public ResponseEntity<List<PlanetDTO>> findPlanetByName(@PathVariable String name) {
        List<PlanetDTO> planetList = planetService.findByNameIgnoreCase(name);
        planetList.forEach(p -> {
            PlanetSwapiPaginationResponse planetsApi = clientFeignSwapi.getPlanetsByName(p.getName());
            planetsApi.getResults().forEach(swapiPlanet ->
                    p.setFilmsAppearances(swapiPlanet.getFilms().size() + p.getFilmsAppearances())
            );
        });
        return ResponseEntity.ok().body(planetList);
    }

    @PostMapping
    public ResponseEntity<PlanetDTO> insertPlanet(@RequestBody @Valid PlanetDTO planetDTO) {
        planetDTO = planetService.insertPlanet(planetDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(planetDTO.getIdPlanet()).toUri();
        return ResponseEntity.created(uri).body(planetDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PlanetDTO> deletePlanet(@PathVariable Long id) {
        planetService.deletePlanet(id);
        return ResponseEntity.noContent().build();
    }
}
