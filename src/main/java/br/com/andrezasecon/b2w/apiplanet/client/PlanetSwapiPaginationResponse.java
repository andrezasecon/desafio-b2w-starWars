package br.com.andrezasecon.b2w.apiplanet.client;

import br.com.andrezasecon.b2w.apiplanet.domain.SwapiPlanet;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetSwapiPaginationResponse {
    private Integer count;
    private String next;
    private String previous;
    private List<SwapiPlanet> results;

}

