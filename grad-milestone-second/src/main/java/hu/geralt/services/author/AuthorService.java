package hu.geralt.services.author;

import hu.geralt.domain.Author;

public interface AuthorService {

    Iterable<Author> findAll();

}
