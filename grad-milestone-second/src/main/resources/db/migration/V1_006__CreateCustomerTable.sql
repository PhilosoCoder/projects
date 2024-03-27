CREATE TABLE customer (
    customer_id UUID PRIMARY KEY NOT NULL,
    customer_name TEXT,
    version INTEGER,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);