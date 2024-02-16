package hu.geralt.services;

import hu.geralt.domain.Author;

public interface AuthorService {

    Iterable<Author> findAll();

}
