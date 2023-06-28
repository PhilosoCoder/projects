package airplanes.dtos;

import airplanes.model.AirplaneType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirplaneDto {

    private Long id;

    private AirplaneType airplaneType;

    private String ownerAirline;

    private Set<RouteDto> routes;
}
