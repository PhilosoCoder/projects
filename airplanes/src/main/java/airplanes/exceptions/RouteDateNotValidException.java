package airplanes.exceptions;

import java.time.LocalDate;

public class RouteDateNotValidException extends RuntimeException {

    public RouteDateNotValidException(LocalDate dateOfFlight) {
        super("Flight is not free on " + dateOfFlight);
    }
}
