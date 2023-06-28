package airplanes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException e) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        detail.setType(URI.create("airplanes/not-valid"));
        return detail;
    }

    @ExceptionHandler(AirplaneNotFoundException.class)
    public ProblemDetail handleAirPlaneNotFound(AirplaneNotFoundException e) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        detail.setType(URI.create("airplanes/not-found"));
        return detail;
    }
    @ExceptionHandler(RouteNotFoundException.class)
    public ProblemDetail handleRoutePlaneNotFound(RouteNotFoundException e) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        detail.setType(URI.create("routes/not-found"));
        return detail;
    }

    @ExceptionHandler(RouteDateNotValidException.class)
    public ProblemDetail handleRouteDateNotValid(RouteDateNotValidException e) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        detail.setType(URI.create("airplanes/not-valid"));
        return detail;
    }
}
