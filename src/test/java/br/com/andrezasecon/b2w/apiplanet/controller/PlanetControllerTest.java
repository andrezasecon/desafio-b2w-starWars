package br.com.andrezasecon.b2w.apiplanet.controller;

import br.com.andrezasecon.b2w.apiplanet.client.ClientFeignSwapi;
import br.com.andrezasecon.b2w.apiplanet.client.PlanetSwapiPaginationResponse;
import br.com.andrezasecon.b2w.apiplanet.service.PlanetService;
import br.com.andrezasecon.b2w.apiplanet.service.PlanetServiceImpl;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@WebMvcTest
public class PlanetControllerTest {

    @Autowired
    private PlanetController planetController;
    @Mock
    private PlanetServiceImpl planetService;

    @BeforeEach
    public void setup(){
        RestAssuredMockMvc.standaloneSetup(this.planetController);
    }

    @Test
    public void mustReturnSuccess_whenFindAllPlanets(){
        Mockito.when(this.planetService.findAllPlanets()).thenReturn(new ArrayList<>());

        RestAssuredMockMvc.given()
            .accept(ContentType.JSON)
            .when ()
                .get("/planetas")
            .then()
                .statusCode(HttpStatus.OK.value());

    }
}
