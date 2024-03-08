package hu.geralt.repositories.beer;

import java.util.UUID;

import hu.geralt.entities.beer.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository <Beer, UUID> {
}