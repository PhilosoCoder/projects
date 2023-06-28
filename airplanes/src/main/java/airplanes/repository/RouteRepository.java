package airplanes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import airplanes.model.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {
}
