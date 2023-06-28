package airplanes.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import airplanes.dtos.AirplaneDto;
import airplanes.dtos.CreateAirplaneCommand;
import airplanes.dtos.CreateRouteCommand;
import airplanes.dtos.RouteDto;
import airplanes.service.AirplaneRouteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/airplanes")
public class AirplaneRouteController {

    private final AirplaneRouteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AirplaneDto saveAirplane(@RequestBody @Valid CreateAirplaneCommand command) {
        return service.saveAirplane(command);
    }

    @PostMapping("/{id}/routes")
    @ResponseStatus(HttpStatus.CREATED)
    public RouteDto saveRouteToAirplane(@PathVariable("id") long id, @RequestBody @Valid CreateRouteCommand command) {
        return service.saveRouteToAirplane(id, command);
    }

    @GetMapping
    public List<AirplaneDto> findAllAirplanesByAirline(@RequestParam Optional<String> ownerAirline) {
        return service.findAllAirplanesByAirline(ownerAirline);
    }

    @PutMapping("/{id}/routes/{routeId}")
    public AirplaneDto cancelFlight(@PathVariable("id") long planeId, @PathVariable("routeId") long routeId){
        return service.cancelFlight(planeId,routeId);
    }
}
