CREATE TABLE category (
    category_id UUID NOT NULL PRIMARY KEY,
    description TEXT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP DEFAULT NULL,
    version BIGINT DEFAULT NULL
)