package hu.geralt.services.book;

import hu.geralt.domain.Book;

public interface BookService {

    Iterable<Book> findAll();

}
