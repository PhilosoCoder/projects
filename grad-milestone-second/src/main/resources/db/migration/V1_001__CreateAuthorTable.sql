CREATE TABLE author (
    author_id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    first_name TEXT,
    last_name TEXT
)