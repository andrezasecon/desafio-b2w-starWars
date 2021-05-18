package br.com.andrezasecon.b2w.apiplanet.client;

import br.com.andrezasecon.b2w.apiplanet.domain.SwapiPlanet;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetSwapiPaginationResponse {
    private Integer count;
    private String next;
    private String previous;
    private List<SwapiPlanet> results;

    public PlanetSwapiPaginationResponse() {
    }

    public PlanetSwapiPaginationResponse(Integer count, String next, String previous, List<SwapiPlanet> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<SwapiPlanet> getResults() {
        return results;
    }

    public void setResults(List<SwapiPlanet> results) {
        this.results = results;
    }
}

