CREATE TABLE book (
    book_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title TEXT,
    isbn TEXT,
    publisher BIGINT,

    CONSTRAINT fk_book_publisher_id FOREIGN KEY (publisher) REFERENCES publisher (publisher_id)
)
