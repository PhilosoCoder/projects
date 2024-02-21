package hu.geralt.services.book.book;

import hu.geralt.model.book.Book;

public interface BookService {

    Iterable<Book> findAll();

}
