package br.com.andrezasecon.b2w.apiplanet.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "swapiplanets", url = "https://swapi.dev/api/")
public interface ClientFeignSwapi {


    @GetMapping(value = "/planets/?search={name}")
    PlanetSwapiPaginationResponse getPlanetsByName(@PathVariable String name);
}
