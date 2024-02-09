CREATE TABLE publisher (
    publisher_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    publisher_name TEXT,
    address TEXT,
    city TEXT,
    state TEXT,
    zip TEXT
);
