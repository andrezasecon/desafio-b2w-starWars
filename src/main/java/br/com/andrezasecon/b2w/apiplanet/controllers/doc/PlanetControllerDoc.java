package br.com.andrezasecon.b2w.apiplanet.controllers.doc;

import br.com.andrezasecon.b2w.apiplanet.dto.PlanetDTO;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
    ResponseEntity<List<PlanetDTO>> findAllPlanets();

    @ApiOperation(value = "Planet by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Planet returned successfully."),
            @ApiResponse(code = 400, message = "Bad request."),
            @ApiResponse(code = 404, message = "Resource Not Found.")

    })
    ResponseEntity<List<PlanetDTO>> findPlanetById(
            @ApiParam(value = "Id of planet", required = true, example = "1")
            @PathVariable Long id);

    @ApiOperation(value = "Planet by name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Planet returned successfully."),
            @ApiResponse(code = 400, message = "Bad request."),
            @ApiResponse(code = 404, message = "Resource Not Found.")
    })
    ResponseEntity<List<PlanetDTO>>  findPlanetByName(
            @ApiParam(value = "Name of planet", required = true, example = "Tatooine")
            @PathVariable String name);

    @ApiOperation(value = "Insert planet")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Planet created successfully."),
            @ApiResponse(code = 400, message = "Bad request."),
            @ApiResponse(code = 409, message = "Conflict.")
    })
    ResponseEntity<PlanetDTO> insertPlanet(@RequestBody @Valid PlanetDTO objPlanet);

    @ApiOperation(value = "Delete planet")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Planet deleted successfully."),
            @ApiResponse(code = 400, message = "Bad request."),
            @ApiResponse(code = 409, message = "Conflict.")
    })
    ResponseEntity<PlanetDTO> deletePlanet(
            @ApiParam(value = "Id of planet", required = true, example = "1")
            @PathVariable Long id);

}
