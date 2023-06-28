package airplanes.exceptions;

public class RouteNotFoundException extends RuntimeException {
    public RouteNotFoundException(long routeId) {
        super("Route not found with id: "+routeId);
    }
}
