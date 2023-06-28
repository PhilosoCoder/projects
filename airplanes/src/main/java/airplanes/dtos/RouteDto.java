package airplanes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteDto {

    private Long id;

    private String departureCity;

    private String arrivalCity;

    private LocalDate dateOfFlight;
}
