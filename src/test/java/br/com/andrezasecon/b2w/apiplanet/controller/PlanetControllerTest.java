package br.com.andrezasecon.b2w.apiplanet.controller;

import br.com.andrezasecon.b2w.apiplanet.ApiplanetApplication;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.concurrent.TimeUnit;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;


@TestPropertySource("classpath:/test.properties")
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ApiplanetApplication.class)
@AutoConfigureMockMvc
public class PlanetControllerTest {

    private static final String PLANET_URL = "/api/find/%s";
    @Autowired
    protected MockMvc mvc;

    private static ClientAndServer mockServer;

    @BeforeClass
    public static void startServer() {
        mockServer = startClientAndServer(1088);
    }

    @AfterClass
    public static void stopServer() {
        mockServer.stop();
    }

    @Test
    public void searchPlanetByName() throws Exception {
        createMockApiFindPlanetByName_tatooine();
        String planetName = "Tatooine";
        String urlTemplate = String.format(PLANET_URL, planetName);
        mvc.perform(
                MockMvcRequestBuilders.get(urlTemplate))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Tatooine"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].climate").value("arid"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].terrain").value("desert"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].filmsAppearances").value("6"));
    }

    private void createMockApiFindPlanetByName_tatooine() {
        new MockServerClient("localhost", 1088)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/planets/")
                                .withQueryStringParameter("search=Tatooine")
                        ,
                        exactly(1))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json"))
                                .withBody("{\n" +
                                        "    \"count\": 1,\n" +
                                        "    \"next\": null,\n" +
                                        "    \"previous\": null,\n" +
                                        "    \"results\": [\n" +
                                        "        {\n" +
                                        "            \"name\": \"Tatooine\",\n" +
                                        "            \"rotation_period\": \"23\",\n" +
                                        "            \"orbital_period\": \"304\",\n" +
                                        "            \"diameter\": \"10465\",\n" +
                                        "            \"climate\": \"arid\",\n" +
                                        "            \"gravity\": \"1 standard\",\n" +
                                        "            \"terrain\": \"desert\",\n" +
                                        "            \"surface_water\": \"1\",\n" +
                                        "            \"population\": \"200000\",\n" +
                                        "            \"residents\": [\n" +
                                        "                \"http://swapi.dev/api/people/1/\",\n" +
                                        "                \"http://swapi.dev/api/people/2/\",\n" +
                                        "                \"http://swapi.dev/api/people/4/\",\n" +
                                        "                \"http://swapi.dev/api/people/6/\",\n" +
                                        "                \"http://swapi.dev/api/people/7/\",\n" +
                                        "                \"http://swapi.dev/api/people/8/\",\n" +
                                        "                \"http://swapi.dev/api/people/9/\",\n" +
                                        "                \"http://swapi.dev/api/people/11/\",\n" +
                                        "                \"http://swapi.dev/api/people/43/\",\n" +
                                        "                \"http://swapi.dev/api/people/62/\"\n" +
                                        "            ],\n" +
                                        "            \"films\": [\n" +
                                        "                \"http://swapi.dev/api/films/1/\",\n" +
                                        "                \"http://swapi.dev/api/films/3/\",\n" +
                                        "                \"http://swapi.dev/api/films/4/\",\n" +
                                        "                \"http://swapi.dev/api/films/5/\",\n" +
                                        "                \"http://swapi.dev/api/films/6/\",\n" +
                                        "                \"http://swapi.dev/api/films/7/\"\n" +
                                        "            ],\n" +
                                        "            \"created\": \"2014-12-09T13:50:49.641000Z\",\n" +
                                        "            \"edited\": \"2014-12-20T20:58:18.411000Z\",\n" +
                                        "            \"url\": \"http://swapi.dev/api/planets/1/\"\n" +
                                        "        }\n" +
                                        "    ]\n" +
                                        "}")
                                .withDelay(TimeUnit.SECONDS, 1)
                );
    }
}
