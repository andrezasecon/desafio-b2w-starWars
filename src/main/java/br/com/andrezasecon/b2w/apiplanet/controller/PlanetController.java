package br.com.andrezasecon.b2w.apiplanet.controller;

import br.com.andrezasecon.b2w.apiplanet.domain.Planet;
import br.com.andrezasecon.b2w.apiplanet.service.PlanetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/planets")
public class PlanetController {

    @Autowired
    private PlanetServiceImpl planetService;

    @GetMapping
    public List<Planet> findAll(){
        return planetService.findAllPlanets();
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Planet> findPlanetById(@PathVariable String id){
        return planetService.findPlanetById(id).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping

    @PostMapping
    public void insertPlanet(@RequestBody Planet objPlanet, HttpServletResponse response ){
        planetService.insertPlanet(objPlanet);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }


}
