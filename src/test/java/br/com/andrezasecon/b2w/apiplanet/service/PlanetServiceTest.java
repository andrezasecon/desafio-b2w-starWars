package br.com.andrezasecon.b2w.apiplanet.service;

import br.com.andrezasecon.b2w.apiplanet.domains.Planet;
import br.com.andrezasecon.b2w.apiplanet.dto.PlanetDTO;
import br.com.andrezasecon.b2w.apiplanet.repositories.PlanetRepository;
import br.com.andrezasecon.b2w.apiplanet.services.PlanetServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

    @InjectMocks
    PlanetServiceImpl planetService;

    @Mock
    PlanetRepository planetRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findPlanetsTest() {

        List<Planet> lista = Arrays.asList(
                new Planet(1L, "Tatooine", "arid", "desert"),
                new Planet(1L, "Alderaan", "temperate", "grasslands, mountains")
        );

        Page<Planet> planetPage = new PageImpl<Planet>(lista);

        when(planetRepository.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(planetPage);

        PageRequest pageRequest = PageRequest.of(0, 12, Sort.Direction.valueOf("ASC"), "name");
        Page<PlanetDTO> planetsDto = planetService.findAllPlanetsPaged(pageRequest);

        assertEquals(planetsDto.getTotalElements(), 2);
        assertEquals(planetsDto.getTotalPages(), 1);

    }

    @Test
    public void findPlanetByNameTest() {

        List<Planet> list = Arrays.asList(
                new Planet("Tatooine", "arid", "desert"),
                new Planet("Alderaan", "temperate", "grasslands, mountains")
        );

        String name = "Tatooine";
        when(planetRepository.findByNameIgnoreCase(name)).thenReturn(list);

        List<PlanetDTO> planetsDto = planetService.findByNameIgnoreCase(name);
        assertThat(planetsDto, hasSize(2));
        assertEquals("Tatooine", name);
    }

    @Test
    public void findPlanetByIdTest() {

        String name = "Tatooine";

        List<Planet> list = Arrays.asList(
                new Planet(1L, name, "arid", "desert"));

        Long idPlanet = 1L;
        when(planetRepository.findPlanetByIdPlanet(idPlanet)).thenReturn(list);

        List<PlanetDTO> dto = planetService.findPlanetById(idPlanet);
        assertThat(dto, hasSize(1));
        assertEquals(name, list.get(0).getName());
    }

    @Test
    public void deletePlanetByIdTest() {

        List<Planet> list = Arrays.asList(
                new Planet(1L, "Tatooine", "arid", "desert"));
        Long idPlanet = 1L;
        when(planetRepository.findPlanetByIdPlanet(idPlanet)).thenReturn(list);
        planetService.deletePlanet(idPlanet);
    }
}