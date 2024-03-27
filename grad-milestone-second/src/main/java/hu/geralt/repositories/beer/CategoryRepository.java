package hu.geralt.repositories.beer;

import java.util.UUID;

import hu.geralt.entities.beer.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
