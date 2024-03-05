package hu.geralt.repositories.book;

import java.util.Optional;

import hu.geralt.entities.book.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByLastName(String lastName);
}
