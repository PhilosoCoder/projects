package hu.geralt.repositories.beer;

import java.util.UUID;

import hu.geralt.entities.beer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
