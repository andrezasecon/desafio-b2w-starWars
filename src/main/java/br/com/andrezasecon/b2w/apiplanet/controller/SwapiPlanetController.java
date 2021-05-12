package br.com.andrezasecon.b2w.apiplanet.controller;

import br.com.andrezasecon.b2w.apiplanet.client.ClientFeignSwapi;
import br.com.andrezasecon.b2w.apiplanet.client.PlanetSwapiPaginationResponse;
import br.com.andrezasecon.b2w.apiplanet.domain.SwapiPlanet;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("planets")
public class SwapiPlanetController {

    private ClientFeignSwapi clientFeignSwapi;

    @GetMapping
    public PlanetSwapiPaginationResponse getPlanetByName(String name){
        return clientFeignSwapi.getPlanetsByName(name);
    }
}
