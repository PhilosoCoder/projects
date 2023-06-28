package airplanes.dtos;

import airplanes.model.AirplaneType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateAirplaneCommand {

    private AirplaneType airplaneType;

    @NotBlank
    private String ownerAirline;
}
