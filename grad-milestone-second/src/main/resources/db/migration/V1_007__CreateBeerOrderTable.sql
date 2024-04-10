CREATE TABLE beer_order (
    beer_order_id UUID PRIMARY KEY NOT NULL,
    created_at TIMESTAMP DEFAULT NULL,
    customer_ref TEXT DEFAULT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    version bigint DEFAULT NULL,
    customer_id UUID DEFAULT NULL,

    CONSTRAINT fk_beer_order_customer_id FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
)