package hu.geralt.services;

import hu.geralt.domain.Book;

public interface BookService {

    Iterable<Book> findAll();

}
