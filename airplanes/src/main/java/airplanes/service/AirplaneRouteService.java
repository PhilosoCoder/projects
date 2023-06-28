package airplanes.service;

import airplanes.exceptions.AirplaneNotFoundException;
import airplanes.exceptions.RouteDateNotValidException;
import airplanes.exceptions.RouteNotFoundException;
import airplanes.mappers.AirplaneMapper;
import airplanes.repository.AirplaneRepository;
import airplanes.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import airplanes.dtos.AirplaneDto;
import airplanes.dtos.CreateAirplaneCommand;
import airplanes.dtos.CreateRouteCommand;
import airplanes.dtos.RouteDto;
import airplanes.mappers.RouteMapper;
import airplanes.model.Airplane;
import airplanes.model.Route;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirplaneRouteService {

    private final AirplaneRepository airplaneRepository;

    private final RouteRepository routeRepository;

    private final AirplaneMapper airplaneMapper;

    private final RouteMapper routeMapper;

    @Transactional
    public AirplaneDto saveAirplane(CreateAirplaneCommand command) {
        Airplane airplane = new Airplane(command.getAirplaneType(), command.getOwnerAirline());
        airplaneRepository.save(airplane);
        return airplaneMapper.toDto(airplane);
    }

    @Transactional
    public RouteDto saveRouteToAirplane(long id, CreateRouteCommand command) {
        Airplane airplane = airplaneRepository.findById(id).orElseThrow(() -> new AirplaneNotFoundException(id));
        if (!isFlightFreeOnDate(airplane, command.getDateOfFlight())) {
            throw new RouteDateNotValidException(command.getDateOfFlight());
        }
        Route route = new Route(command.getDepartureCity(), command.getArrivalCity(), command.getDateOfFlight());
        route.setAirplane(airplane);
        routeRepository.save(route);
        return routeMapper.toDto(route);
    }

    private boolean isFlightFreeOnDate(Airplane airplane, LocalDate date) {
        return airplane.getRoutes().stream()
                .map(Route::getDateOfFlight)
                .filter(d -> d.equals(date)).toList().isEmpty();
    }

    public List<AirplaneDto> findAllAirplanesByAirline(Optional<String> ownerAirline) {
        List<Airplane> result = airplaneRepository.findAllByOwnerAirLine(ownerAirline);
        return airplaneMapper.toDto(result);
    }

    @Transactional
    public AirplaneDto cancelFlight(long planeId, long routeId) {
        Airplane airplane = airplaneRepository.findById(planeId).orElseThrow(()->new AirplaneNotFoundException(planeId));
        Route route = routeRepository.findById(routeId).orElseThrow(()->new RouteNotFoundException(routeId));
        if(route.getAirplane()==null || route.getAirplane().getId()!=airplane.getId()){
            throw new RouteNotFoundException(routeId);
        }
        route.setAirplane(null);
        airplane.getRoutes().remove(route);
        return airplaneMapper.toDto(airplane);
    }
}
