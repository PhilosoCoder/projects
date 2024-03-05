package hu.geralt.repositories.book;

import java.util.Optional;

import hu.geralt.entities.book.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Optional<Publisher> findBypublisherName(String publisherName);
}
