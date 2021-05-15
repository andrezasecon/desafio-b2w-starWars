package br.com.andrezasecon.b2w.apiplanet.controller.doc;

import br.com.andrezasecon.b2w.apiplanet.domain.Planet;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Api(value = "Api planets", tags = {"Api planets"})
public interface PlanetControllerDoc {


    @ApiOperation(value = "List of planets")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of planets returned successfully."),
            @ApiResponse(code = 404, message = "Resource Not Found."),
            @ApiResponse(code = 400, message = "Bad request.")
    })
    List<Planet> findAll();

    @ApiOperation(value = "Planet by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Planet returned successfully."),
            @ApiResponse(code = 400, message = "Bad request."),
            @ApiResponse(code = 404, message = "Resource Not Found.")

    })
    ResponseEntity<Planet> findPlanetById(
            @ApiParam(value = "Id of planet", required = true, example = "11")
            @PathVariable String id);

    @ApiOperation(value = "Planet by name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Planet returned successfully."),
            @ApiResponse(code = 400, message = "Bad request."),
            @ApiResponse(code = 404, message = "Resource Not Found.")
    })
    List<Planet> findPlanetByName(
            @ApiParam(value = "Name of planet", required = true, example = "Tatooine")
            @PathVariable String name, HttpServletResponse response);


    @ApiOperation(value = "Insert planet")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Planet created successfully."),
            @ApiResponse(code = 400, message = "Bad request.")
    })
    void insertPlanet(@RequestBody @Valid Planet objPlanet, HttpServletResponse response);


    @ApiOperation(value = "Delete planet")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Planet deleted successfully."),
            @ApiResponse(code = 400, message = "Bad request."),
            @ApiResponse(code = 404, message = "Resource Not Found.")
    })
    public void deletePlanet(
            @ApiParam(value = "Id of planet", required = true, example = "11")
            @PathVariable String id, HttpServletResponse response);

}
