package hu.geralt.services.book;

import hu.geralt.model.Book;

public interface BookService {

    Iterable<Book> findAll();

}
