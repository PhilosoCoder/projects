package hu.geralt.services.book.author;

import hu.geralt.entities.book.Author;

public interface AuthorService {

    Iterable<Author> findAll();

}
