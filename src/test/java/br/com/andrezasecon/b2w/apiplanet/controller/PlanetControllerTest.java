package br.com.andrezasecon.b2w.apiplanet.controller;

import br.com.andrezasecon.b2w.apiplanet.domains.Planet;
import br.com.andrezasecon.b2w.apiplanet.dto.PlanetDTO;
import br.com.andrezasecon.b2w.apiplanet.repositories.PlanetRepository;
import br.com.andrezasecon.b2w.apiplanet.services.PlanetServiceImpl;
import br.com.andrezasecon.b2w.apiplanet.services.exceptions.ResourceAlreadyExistException;
import br.com.andrezasecon.b2w.apiplanet.services.exceptions.ResourceNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
@SpringBootTest
@TestPropertySource("classpath:/test.properties")
public class PlanetControllerTest {

    @Autowired
    private PlanetServiceImpl planetService;

    @Autowired
    private PlanetRepository planetRepository;

    @Container
    public static MongoDBContainer container = new MongoDBContainer(DockerImageName.parse("mongo:4.4.3"))
            .withExposedPorts(27022);

    @BeforeAll
    static void initAll() {
        container.start();

    }

    @After
    public  void stop(){
        planetRepository.deleteAll();
    }

    @BeforeEach
    public void initApp() {
        planetRepository.deleteAll();
        Planet tatooine = new Planet(22l,"Tatooine", "arid", "desert");

        planetRepository.saveAll(Arrays.asList(tatooine));
    }

    @Test
    void containerStartsAndPublicPortIsAvailable() {
        assertThatPortIsAvailable(container);
    }

    @Test
    void insertPlanetResourceAlreadyExistTestExecption() {
        PlanetDTO dto = new PlanetDTO();
        dto.setName("Tatooine");
        dto.setClimate("Arid");
        dto.setTerrain("Teste");
        assertThrows(ResourceAlreadyExistException.class, () -> {
            planetService.insertPlanet(dto);
        });
    }

    @Test
    void insertPlanetTest() {
        PlanetDTO dto = new PlanetDTO();
        dto.setName("Teste");
        dto.setClimate("Arid");
        dto.setTerrain("Teste");
        planetService.insertPlanet(dto);
        List<PlanetDTO> listReturn = planetService.findByNameIgnoreCase("Teste");

        assertThat(listReturn, hasSize(1));
        assertEquals(listReturn.get(0).getClimate(), dto.getClimate());
        assertEquals(listReturn.get(0).getName(), dto.getName());

    }

    @Test
    void findPlanetByIdTest() {
        assertThrows(ResourceNotFoundException.class, () -> {
            Long idNonInexistent = 100L;
            planetService.findPlanetById(idNonInexistent);
        });
    }

    @Test
    void deletelanetByIdTest() {
        assertThrows(ResourceNotFoundException.class, () -> {
            Long idNonInexistent = 100L;
            planetService.deletePlanet(idNonInexistent);
        });
    }

    private void assertThatPortIsAvailable(MongoDBContainer container) {
        try {
            new Socket(container.getContainerIpAddress(), container.getFirstMappedPort());
        } catch (IOException e) {
            throw new AssertionError("The expected port " + container.getFirstMappedPort() + " is not available!");
        }
    }
}