package hu.geralt.repositories;

import java.util.UUID;

import hu.geralt.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
