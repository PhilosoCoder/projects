package hu.geralt.services.book.author;

import hu.geralt.model.book.Author;

public interface AuthorService {

    Iterable<Author> findAll();

}
