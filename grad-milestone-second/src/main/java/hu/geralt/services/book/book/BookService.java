package hu.geralt.services.book.book;

import hu.geralt.entities.book.Book;

public interface BookService {

    Iterable<Book> findAll();

}
