package airplanes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import airplanes.model.Airplane;

import java.util.List;
import java.util.Optional;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

    @Query("select a from Airplane a where :ownerAirline is null or a.ownerAirline = :ownerAirline")
    List<Airplane> findAllByOwnerAirLine(Optional<String> ownerAirline);
}
