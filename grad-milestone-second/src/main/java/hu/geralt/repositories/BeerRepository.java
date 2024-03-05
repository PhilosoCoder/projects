package hu.geralt.repositories;

import java.util.UUID;

import hu.geralt.entities.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository <Beer, UUID> {
}
