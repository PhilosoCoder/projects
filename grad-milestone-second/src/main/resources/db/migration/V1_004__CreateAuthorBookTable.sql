CREATE TABLE author_book (
    author_id BIGINT,
    book_id BIGINT,

    PRIMARY KEY (author_id, book_id)
);