CREATE TABLE author_book (
    author_id BIGINT,
    book_id BIGINT,
    PRIMARY KEY (author_id, book_id)
--    FOREIGN KEY (author_id) REFERENCES author (author_id),
--    FOREIGN KEY (book_id) REFERENCES book (book_id),
--    FOREIGN KEY (publisher_id) REFERENCES publisher (publisher_id)
);