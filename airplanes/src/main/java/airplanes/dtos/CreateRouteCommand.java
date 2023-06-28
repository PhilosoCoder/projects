package airplanes.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateRouteCommand {

    @NotBlank
    private String departureCity;

    @NotBlank
    private String arrivalCity;

    @Future
    private LocalDate dateOfFlight;
}
