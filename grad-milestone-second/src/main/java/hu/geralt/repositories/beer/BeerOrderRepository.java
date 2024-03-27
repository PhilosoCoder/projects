package hu.geralt.repositories.beer;

import java.util.UUID;

import hu.geralt.entities.beer.BeerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID> {

}
