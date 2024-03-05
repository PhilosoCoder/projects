CREATE TABLE customer (
    customer_id UUID PRIMARY KEY,
    customer_name TEXT,
    version INTEGER,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);