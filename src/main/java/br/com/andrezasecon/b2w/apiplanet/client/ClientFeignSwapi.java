package br.com.andrezasecon.b2w.apiplanet.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Informando ao Spring que essa interface é um cliente Feign
@FeignClient(name = "swapiplanets", url = "https://swapi.dev/api/")
public interface ClientFeignSwapi {

    // Path com parâmetro de busca fornecido pela Swapi
    @GetMapping(value = "/planets/?search={name}")
    PlanetSwapiPaginationResponse getPlanetsByName(@PathVariable String name);
}
