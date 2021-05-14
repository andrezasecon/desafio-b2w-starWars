package br.com.andrezasecon.b2w.apiplanet.controller;

import br.com.andrezasecon.b2w.apiplanet.ApiplanetApplication;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ApiplanetApplication.class)
@AutoConfigureMockMvc
public class PlanetControllerTest {

    private static final String PLANET_URL = "/planetas/find/%s";
    @Autowired
    protected MockMvc mvc;

    @BeforeEach
    public void setup() {

    }


    @Test
    public void searchPlanetByName() throws Exception {
        String planetName = "Tatooine";
        String urlTemplate = String.format(PLANET_URL, planetName);
        mvc.perform(
                MockMvcRequestBuilders.get(urlTemplate))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Tatooine"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].climate").value("arid"));
    }
}
